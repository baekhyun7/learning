package com.zh.learning.exception;


import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.ResponseEnum;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author zh
 * @version 1.0
 * @date 2020/10/20 14:24
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResponseEntity exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:{}",e.getMessage());
        return ResponseEntity.fail(ResponseEnum.EXCEPTION,e.getMessage());
    }
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public ResponseEntity unauthHandler(HttpServletRequest req, Exception e){
        logger.error("权限异常！原因是:{}",e.getMessage());
        return ResponseEntity.fail(ResponseEnum.AUTHORIZATION_ILLEGAL);
    }

    @ExceptionHandler(value =ApiException.class)
    @ResponseBody
    public ResponseEntity apiExceptionHandler(HttpServletRequest req, ApiException e){
        logger.error("接口异常！原因是:{}",e.getMessage());
        return ResponseEntity.fail(ResponseEnum.EXCEPTION,e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity paramExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        logger.error("参数校验异常！原因是:{}",e.getMessage());
        return ResponseEntity.fail(ResponseEnum.EXCEPTION,e.getBindingResult().getFieldError().getDefaultMessage());
    }


}
