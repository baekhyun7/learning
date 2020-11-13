package com.zh.learning.controller;

import com.zh.learning.constants.ApiConstants;
import com.zh.learning.util.TokenUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zh
 * @version 1.0
 * @date 2020/10/22 16:52
 */
@Api(value = "base")
@RestController
@RequestMapping("/base")
public class BaseController {

    /**
     * 得到当前token
     *
     * @param request
     * @return
     * @author candy
     */
    public static String getToken(HttpServletRequest request) {
        String uniqueToken = request.getHeader(ApiConstants.TOKEN);
        if (uniqueToken == null) {
            // 请求头里面没有token，则从cookie中获取
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && ApiConstants.TOKEN.equals(cookie.getName())) {
                        uniqueToken = cookie.getValue();
                    }
                }
            }
        }
        return uniqueToken;
    }

    /**
     * 得到当前token
     *
     * @return
     * @author candy
     */
    public static String getUserId() {
        String token = getToken(getRequest());
        String userId = TokenUtil.getUserId(token);
        return userId;
    }

    /**
     * 从线程中获取request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
