package com.zh.learning.annotation;

import com.zh.learning.constants.RedisLockTypeEnum;

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
public @interface RedisLockRequired {
    /**
     * 特定参数识别，默认取第 0 个下标
     */
    String lockFiled() default "0";

    /**
     * 超时重试次数
     */
    int tryCount() default 3;

    /**
     * 自定义加锁类型
     */
    RedisLockTypeEnum typeEnum();

    /**
     * 释放时间，秒 s 单位
     */
    int lockTime() default 30;
}
