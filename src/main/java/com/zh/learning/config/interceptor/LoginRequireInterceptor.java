package com.zh.learning.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zh.learning.annotation.LoginRequire;
import com.zh.learning.constants.ApiConstants;
import com.zh.learning.controller.BaseController;
import com.zh.learning.service.RedisService;
import com.zh.learning.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zh
 * @version 1.0
 * @date 2021/2/3 9:46
 */
@Component
public class LoginRequireInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            //获取方法上的注解
            LoginRequire methodAnnotation = hm.getMethodAnnotation(LoginRequire.class);
            if(Objects.nonNull(methodAnnotation)){
                String token = BaseController.getToken(request);
                if(StringUtils.isBlank(token)){
                    onLoginFail(request,response);
                    return false;
                }else{
                    String userId = TokenUtil.getUserId(token);
                    if(StringUtils.isBlank(redisService.getToken(userId))){
                        onLoginFail(request,response);
                        return false;
                    }else{
                        redisService.putToken(userId,token);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 登录失败时默认返回401状态码
     * @param request
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletRequest request, ServletResponse response)
            throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        response.setContentType("application/json;charset=UTF-8");
        // 返回用户没有登录错误
        Map<String, Object> map = new HashMap<>(16);
        map.put(ApiConstants.CODE, ApiConstants.AUTHENTICATION_ILLEGAL_CODE);
        map.put(ApiConstants.MSG, ApiConstants.TOKEN_ILLEGAL_MESSAGE);
        httpResponse.getWriter().write(JSONObject.toJSONString(map));
    }
}
