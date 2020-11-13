package com.zh.learning.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作类型枚举
 *
 * @author ydh
 */
public enum LogOperationEnum {

    /**
     * 登录
     */
    LOGIN("登录"),
    /**
     * 登录
     */
    REGISTER("注册"),
    /**
     * 退出
     */
    LOGOUT("退出"),
    /**
     * 新增
     */
    INSERT("新增"),
    /**
     * 修改
     */
    UPDATE("修改"),

    /**
     * 删除
     */
    DELETE("删除"),
    /**
     * 上传
     */
    UPLOADE("上传文件"),
    /**
     * 下载
     */
    DOWNLOAD("下载文件"),

    TEST("测试")
    ;


    String operation;

    LogOperationEnum(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
