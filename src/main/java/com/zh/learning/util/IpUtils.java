package com.zh.learning.util;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/12 11:22
 */
public class IpUtils {

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-real-ip");
        }
        if (isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        if (isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }
        if (isBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
}
