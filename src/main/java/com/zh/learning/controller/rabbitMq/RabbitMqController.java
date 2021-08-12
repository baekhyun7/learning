package com.zh.learning.controller.rabbitMq;

import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.service.rabbitMq.MessageProvide;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zh
 * @date 2021-07-13 11:13
 */
@Api(tags = "测试消息队列")
@RestController
@RequestMapping(value = "/sendMessage")
public class RabbitMqController extends BaseController {
    @Autowired
    MessageProvide messageProvide;

    /**
     * 发送消息
     *
     * @return
     */
    @ApiOperation(value = "发送消息")
    @GetMapping
    public ResponseEntity sendMessage(@RequestParam String message) {
        messageProvide.SendMessage(message);
        return ResponseEntity.success("发送消息队列成功");
    }
}
