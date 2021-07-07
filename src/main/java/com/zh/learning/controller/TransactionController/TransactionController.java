package com.zh.learning.controller.TransactionController;

import com.zh.learning.annotation.LogInfo;
import com.zh.learning.constants.ApiConstants;
import com.zh.learning.constants.LogOperationEnum;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.service.sys.UserService;
import com.zh.learning.util.TokenUtil;
import com.zh.learning.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
