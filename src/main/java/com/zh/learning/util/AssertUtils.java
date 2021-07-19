package com.zh.learning.util;

import com.zh.learning.exception.ApiException;

import java.util.Objects;

/***
 *
 * @author zhanghao
 * @date 2021-07-16 10:20
 **/
public class AssertUtils {


    public static void nonNull(Object filed,String msg){
        if(Objects.isNull(filed)){
            throw new ApiException(msg);
        }
    }
}
