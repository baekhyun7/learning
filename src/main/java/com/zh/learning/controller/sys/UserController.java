package com.zh.learning.controller.sys;

import com.zh.learning.annotation.LogInfo;
import com.zh.learning.constants.LogOperationEnum;
import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.ResponseEnum;
import com.zh.learning.service.RedisService;
import com.zh.learning.service.sys.MenuService;
import com.zh.learning.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/12 11:10
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/api/user")
public class UserController extends BaseController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;


    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity getList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "startDate", required = false) String startDate,
                                  @RequestParam(value = "endDate", required = false) String endDate,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "businessId", required = false) String businessId,
                                  @RequestParam(value = "websiteName", required = false) String websiteName) {
        userService.getPageList(pageNum, pageSize);
        return ResponseEntity.success("登录成功", "data");
    }

    @ApiOperation(value = "查询单个用户")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity register(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "id") String id) {
        return ResponseEntity.success("注册成功", "userPo");
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户")
    @LogInfo(LogOperationEnum.INSERT)
    @PostMapping
    public Object add() {
        return ResponseEntity.success(ResponseEnum.SUCCESS.getMsg());
    }

    /**
     * 编辑用户
     */
    @ApiOperation(value = "编辑用户")
    @LogInfo(LogOperationEnum.UPDATE)
    @PutMapping
    public Object update() {
        return ResponseEntity.success(ResponseEnum.SUCCESS.getMsg());
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户")
    @LogInfo(LogOperationEnum.DELETE)
    @DeleteMapping
    public Object delete(@RequestParam(value = "id") String ids) {
        return ResponseEntity.success(ResponseEnum.SUCCESS.getMsg());
    }
}
