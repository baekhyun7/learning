package com.zh.learning.entity;

import lombok.Data;

/**
 * 返回值常量
 *
 * @author zh
 */
@Data
public class ResponseEntity {

    private String msg;

    private int code;

    private Object data;

    public ResponseEntity() {
    }

    public ResponseEntity(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ResponseEntity(ResponseEnum responseEnum, Object data) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = data;
    }

    public ResponseEntity(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
    }

    public static ResponseEntity success(Object data) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.SUCCESS, data);
        return responseEntity;
    }

    public static ResponseEntity success(String msg) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.SUCCESS);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static ResponseEntity success(int code) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.SUCCESS);
        responseEntity.setCode(code);
        return responseEntity;
    }

    public static ResponseEntity success() {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.SUCCESS);
        return responseEntity;
    }

    public static ResponseEntity fail(Object data) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.FAIL, data);
        return responseEntity;
    }

    public static ResponseEntity fail(String msg) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.FAIL);
        responseEntity.setMsg(msg);
        return responseEntity;
    }

    public static ResponseEntity fail(int code) {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.FAIL);
        responseEntity.setCode(code);
        return responseEntity;
    }

    public static ResponseEntity fail() {
        ResponseEntity responseEntity = new ResponseEntity(ResponseEnum.FAIL);
        return responseEntity;
    }
    public static ResponseEntity fail(ResponseEnum responseEnum,Object data) {
        ResponseEntity responseEntity = new ResponseEntity(responseEnum, data);
        return responseEntity;
    }

}
