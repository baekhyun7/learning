package com.zh.learning.controller.transaction;

import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zh
 * @version 1.0
 * @date 2020/10/22 16:52
 */
@Api(value = "事务测试controller")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "post请求")
    @PostMapping
    public ResponseEntity testPost() throws Exception {
        userService.addUser1();
        return ResponseEntity.success();
    }

}
