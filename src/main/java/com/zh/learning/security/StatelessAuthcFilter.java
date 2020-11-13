package com.zh.learning.security;

import com.alibaba.fastjson.JSONObject;
import com.zh.learning.controller.BaseController;
import com.zh.learning.constants.ApiConstants;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro自定义过滤器，用于判断登录状态
 *
 * @author candy
 */

public class StatelessAuthcFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(
            ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    /**
     * isAccessAllowed为false执行，当前设置必定执行
     * @param request
     * @param response
     * @return true过滤器通过，false过滤器不通过
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(
            ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest res = (HttpServletRequest) request;
        //从请求头里面取出token
        String uniqueToken = BaseController.getToken(res);
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        // 生成无状态Token
        StatelessToken stoken = new StatelessToken(uniqueToken, params);
        try {
            // 委托给Realm进行登录
            getSubject(request, response).login(stoken);
            // 更新cookie
            Cookie cookie = new Cookie(ApiConstants.TOKEN, uniqueToken);
            cookie.setMaxAge(ApiConstants.DEFAULT_COOKIE_TIMEOUT);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            ((HttpServletResponse)response).addCookie(cookie);
        } catch (Exception e) {
            // 登录认证失败
            onLoginFail(request, response);
            return false;
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
