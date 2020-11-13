package com.zh.learning.constants;

/**
 * @program: web-backend-template
 * @description: 正则
 * @author: ld
 * @create: 2019-03-18 16:26
 **/
public class RegxpConstants {

    private RegxpConstants() {
    }

    /**
     * 邮箱
     */
    public static final String IS_MAIL = "^([\\w-\\.]+)@([\\w-\\.]+)(\\.[a-zA-Z0-9]+)$";

    /**
     * 中文数字英文
     */
    public static final String CHINESE_CASE_NUM = "^[\u4e00-\u9fa5a-zA-Z0-9]+$";

    /**
     * 英文数字
     */
    public static final String CASE_NUM = "^[\\.a-zA-Z0-9]+$";
}
