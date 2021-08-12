package com.zh.learning.controller.from;

import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.po.from.FromPropertiesPo;
import com.zh.learning.service.FromService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zh
 * @date 2021/7/8
 */
@Api(tags = "测试from")
@RestController
@RequestMapping(value = "/from")
public class FromController extends BaseController {

    @Autowired
    FromService fromService;

    @ApiOperation(value = "查询单个")
    @GetMapping
    public ResponseEntity getIndex(@RequestParam(value = "id") Long id) {
        List<FromPropertiesPo> info = fromService.getInfo(id);
        return ResponseEntity.success(fromService.getInfo(id));
    }

}
