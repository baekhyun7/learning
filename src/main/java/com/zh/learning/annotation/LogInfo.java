package com.zh.learning.annotation;

import com.zh.learning.entity.LogOperationEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/4 16:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogInfo {
    /**
     * 操作日志名称，比如登录、新增
     */
    LogOperationEnum value();
}
