package com.zh.learning.exception;

/**
 * 列如定义接口异常
 *
 * @author zh
 * @version 1.0
 * @date 2020/10/20 14:32
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
