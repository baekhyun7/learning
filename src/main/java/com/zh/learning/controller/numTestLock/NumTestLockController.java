package com.zh.learning.controller.numTestLock;

import com.zh.learning.annotation.RedisLockRequired;
import com.zh.learning.constants.RedisLockTypeEnum;
import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.ResponseEnum;
import com.zh.learning.entity.po.sys.NumTestPo;
import com.zh.learning.service.numTestLock.NumTestLockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zh
 * @date 2021-07-13 11:13
 */
@Api(tags = "测试锁")
@RestController
@RequestMapping(value = "/num")
public class NumTestLockController extends BaseController {
    @Autowired
    NumTestLockService numTestLockService;

    /**
     * 查询单个num资源
     *
     * @return
     */
    @ApiOperation(value = "查询单个num资源")
    @GetMapping
    public ResponseEntity getNum() {
        NumTestPo byId = numTestLockService.getById(1);
        return ResponseEntity.success("查询单个num资源", byId.getNum());
    }

    /**
     * 测试锁
     */
    @ApiOperation(value = "测试锁")
    @PutMapping
    @RedisLockRequired(lockFiled = "1", typeEnum = RedisLockTypeEnum.BUSSINESS, lockTime = 100)
    public Object update() {
        NumTestPo byId = numTestLockService.getById(1);
        Integer integer = byId.getNum();
        byId.setNum(integer + 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numTestLockService.updateById(byId);
        return ResponseEntity.success(ResponseEnum.SUCCESS.getMsg());
    }

    /**
     * 修改num资源
     */
    @ApiOperation(value = "修改num资源=0")
    @PutMapping("/zero")
    public Object updateZero() {
        NumTestPo byId = new NumTestPo();
        byId.setId(1);
        byId.setNum(0);
        numTestLockService.updateById(byId);
        return ResponseEntity.success(ResponseEnum.SUCCESS.getMsg());
    }

}
