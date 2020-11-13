package com.zh.learning.util;

import java.util.UUID;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/13 10:46
 */
public class IdUtils {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
