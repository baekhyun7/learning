package com.zh.learning.config;

import com.alibaba.fastjson.JSON;
import com.zh.learning.annotation.LogInfo;
import com.zh.learning.constants.ApiConstants;
import com.zh.learning.controller.BaseController;
import com.zh.learning.dao.sys.LogDao;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.po.sys.LogPo;
import com.zh.learning.entity.po.sys.UserPo;
import com.zh.learning.service.RedisService;
import com.zh.learning.service.es.LogElasticSearchService;
import com.zh.learning.util.IpUtils;
import com.zh.learning.util.TokenUtil;
import com.zh.learning.vo.request.ElasticSearchLogAddIndexReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * controller调试日志和访问操作日志aop切面类
 *
 * @author ydh
 */
@Component
@Aspect
@Slf4j
public class LogAspection {

    @Autowired
    LogDao logDao;
    @Autowired
    LogElasticSearchService logElasticSearchService;

    @Autowired
    RedisService redisService;

    /**
     * 切入所有controller的public方法
     */
    @Pointcut("execution(public * com.zh.learning.controller..*.*(..))")
    private void logPoint() {
    }

    private static final String LOGIN_PATH = "/anon/login";
    private static final String REGISTER_PATH = "/anon/register";

    /**
     * 定义环绕通知，在访问时记录访问信息，在离开时记录结果
     */
    @Around("logPoint()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = BaseController.getRequest();
        // 获取操作名称
        LogInfo logInfo = ((MethodSignature) jp.getSignature())
                .getMethod().getDeclaredAnnotation(LogInfo.class);
        String operation = "";
        if (Objects.nonNull(logInfo)) {
            operation = logInfo.value().getOperation();
        }
        Object obj = jp.proceed();
        if (request.getRequestURI() != null && request.getRequestURI().contains(LOGIN_PATH) || request.getRequestURI().contains(REGISTER_PATH)) {
            // 登录日志需要特殊处理
            saveOperLog(createLogVoForLogin(request, operation, obj));
        } else {
            // 记录操作日志
            saveOperLog(createLogVoForOperation(request, operation, obj));
        }
        return obj;
    }

    /**
     * 操作日志记录，需要存数据库
     */
    private void saveOperLog(LogPo logPo) {
        logDao.insert(logPo);
        ElasticSearchLogAddIndexReq req = new ElasticSearchLogAddIndexReq();
        req.setLogPo(logPo);
        req.setIndexName("log");
        logElasticSearchService.addDoc(req);
    }

    /**
     * 登录特殊处理，用户id需要从obj中获取
     *
     * @param obj 切点方法执行的结果对象
     */
    private LogPo createLogVoForLogin(HttpServletRequest request, String operation, Object obj) {

        String userId = "";
        String msg = "";
        String resStatus = SUCCESS_STRING;
        if (obj instanceof ResponseEntity) {
            ResponseEntity map = (ResponseEntity) obj;
            msg = map.getMsg();

            // 获取userId
            String token = BaseController.getToken(request);
            if (Objects.nonNull(token)) {
                userId = TokenUtil.getUserId(token);
            } else {
                if (map.getData() instanceof UserPo) {
                    userId = ((UserPo) map.getData()).getId();
                }
            }
            int code = map.getCode();

            if (!Integer.valueOf(ApiConstants.SUCCESS_CODE).equals(code)) {
                resStatus = FAIL_STRING;
            }
        }


        return createLogVo(request, operation, resStatus, msg, userId);
    }

    /**
     * 登录特殊处理，用户id需要从obj中获取
     *
     * @param obj 切点方法执行的结果对象
     */
    private LogPo createLogVoForOperation(HttpServletRequest request, String operation, Object obj) {

        String userId = "";
        String msg = "";
        String resStatus = SUCCESS_STRING;
        if (obj instanceof ResponseEntity) {
            ResponseEntity map = (ResponseEntity) obj;
            msg = map.getMsg();
            int code = map.getCode();
            // 获取userId
            String token = BaseController.getToken(request);
            if (Objects.nonNull(token)) {
                userId = TokenUtil.getUserId(token);
            }
            if (!Integer.valueOf(ApiConstants.SUCCESS_CODE).equals(code)) {
                resStatus = FAIL_STRING;
            }
        }

        return createLogVo(request, operation, resStatus, msg, userId);
    }


    private LogPo createLogVo(HttpServletRequest request, String operation, String resStatus, String msg, String userId) {
        LogPo po = new LogPo();
        po.setOperation(operation);
        if (StringUtils.isNotBlank(userId)) {
            po.setUserId(userId);
            UserPo byId = redisService.getUserInfo(userId);
            po.setUserName(byId.getName());
            po.setUserEmail(byId.getEmail());
        }
        po.setMsg(msg);
        po.setResStatus(resStatus);
        po.setUri(request.getRequestURI());
        po.setIp(IpUtils.getRemoteAddr(request));
        po.setCreateTime(new Date());
        return po;
    }

    private String getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        if (request.getParameterMap() != null) {
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                params.put(entry.getKey(), Arrays.toString(entry.getValue()));
            }
        }
        if (params.isEmpty()) {
            return null;
        }
        return JSON.toJSONString(params);
    }

    private String getBody(HttpServletRequest request) {
        // 不是get，并且content-type是application/json，否则上传文件body非常大
        if (!"GET".equals(request.getMethod()) && "application/json".equals(request.getContentType())) {
            ShiroHttpServletRequest shiroHttpServletRequest = (ShiroHttpServletRequest) request;
            ContentCachingRequestWrapper contentCachingRequestWrapper =
                    (ContentCachingRequestWrapper) shiroHttpServletRequest.getRequest();
            return new String(contentCachingRequestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        }
        return null;
    }


    /**
     * 跳过uri的缓存set
     */
    private static Set<String> uriSet;
    private static final Object LOCK = new Object();

    /**
     * 获取没有ContentPath的url
     */
    private String urlWithoutContentPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        if (url.startsWith(contextPath)) {
            url = url.substring(contextPath.length());
        }
        return url;
    }


    /**
     * 成功代码
     */
    private static final String SUCCESS_STRING = "成功";

    /**
     * 失败代码
     */
    private static final String FAIL_STRING = "失败";

}
