package com.zh.learning.config;

import com.alibaba.fastjson.JSON;
import com.zh.learning.annotation.LogInfo;
import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * controller调试日志和访问操作日志aop切面类
 *
 * @author ydh
 */
@Component
@Aspect
@Slf4j
public class LogAspection {


    /**
     * 切入所有controller的public方法
     */
    @Pointcut("execution(public * com.zh.learning.controller..*.*(..))")
    private void logPoint() {
    }

    private static final String LOGIN_PATH = "/anon/login";

    /**
     * 定义环绕通知，在访问时记录访问信息，在离开时记录结果
     */
    @Around("logPoint()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = BaseController.getRequest();
        // 获取操作名称
        LogInfo logInfo = ((MethodSignature) jp.getSignature())
                .getMethod().getDeclaredAnnotation(LogInfo.class);
        // 获取是否跳过记录日志的标识

        try {

        } catch (Throwable t) {

        } finally {

        }
        return null;
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
    private static final int SUCCESS_CODE = 1;

    /**
     * 失败代码
     */
    private static final int FAIL_CODE = 2;

}
