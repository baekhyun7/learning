package com.zh.learning.controller;

import com.zh.learning.annotation.LogInfo;
import com.zh.learning.entity.LogOperationEnum;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.exception.ApiException;
import com.zh.learning.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zh
 * @version 1.0
 * @date 2020/10/30 11:08
 */
@Api(value = "swagger的实例controller")
@RestController
@RequestMapping("/api/swagger")
public class SwaggerController {

    @ApiOperation(value = "get请求")
    @GetMapping()
    public ResponseEntity testGet(@ApiParam(value = "id" , required=true ) @RequestParam(value = "id") String id){
        if(id.equals("1")){
            throw new ApiException("这个接口有问题");
        }else{
            int i  =  32/0;
        }
        return ResponseEntity.success();
    }

    @ApiOperation(value = "post请求")
    @PostMapping()
    @LogInfo(value = LogOperationEnum.TEST)
    public ResponseEntity testPost(@RequestBody @Validated User user){
        return ResponseEntity.success();
    }

    @ApiOperation(value = "put请求")
    @PutMapping()
    public ResponseEntity testPut(@RequestBody @Validated User user){
        return ResponseEntity.success();
    }
    @ApiOperation(value = "delete请求")
    @DeleteMapping()
    public ResponseEntity testDelete(@ApiParam(value = "id" , required=true ) @RequestParam(value = "id") String id){
        return ResponseEntity.success();
    }

}
