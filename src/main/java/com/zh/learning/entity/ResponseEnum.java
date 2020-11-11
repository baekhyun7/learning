package com.zh.learning.entity;

/**
 * @author zh
 */
public enum ResponseEnum {
    /**
     * 操作成功
     */
    SUCCESS(200, "成功"),

    /**
     * 操作失败
     */
    FAIL(500, "服务器内部错误"),
    TOKEN_FAIL(501, "用户未登录"),

    EXCEPTION(400,"异常操作")
    ;
    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private int code;
    private String msg;
}
