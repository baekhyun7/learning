package com.zh.learning.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ApiConstants;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.entity.po.sys.UserPo;
import com.zh.learning.entity.vo.sys.SysUserLoginVo;
import com.zh.learning.service.RedisService;
import com.zh.learning.service.sys.MenuService;
import com.zh.learning.service.sys.UserService;
import com.zh.learning.util.IpUtils;
import com.zh.learning.util.TokenUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/12 11:10
 */
@Api(value = "登录")
@RestController
@RequestMapping
public class LoginController extends BaseController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;


    @RequestMapping(value = "/anon/login", method = RequestMethod.POST)
    public ResponseEntity login(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid SysUserLoginVo sysUserLoginVO) {
        String userEmail = sysUserLoginVO.getUserEmail();
        String password = sysUserLoginVO.getPassword();
        String ip = IpUtils.getRemoteAddr(request);
        //判断该ip登录失败的次数
        //判断该用户登录失败的次数 如果redis中有值且次数大于5次，说明用户还不可以登录，还在登录限制时间内
        if (emailFailureTime(userEmail)) {
            return ResponseEntity.fail("此用户登录次数过多，请稍后登录");
        }
        //判断用户是否存在
        QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",userEmail);
        UserPo one = userService.getOne(queryWrapper);
        if(Objects.isNull(one)){
            return ResponseEntity.fail("无该用户信息！");
        }else{
            String oldPass = one.getPassword();
            if(!oldPass.equals(password)){
                putEmailFailureTime(userEmail);
                return ResponseEntity.fail("密码错误！");
            }
        }
        redisService.putUserInfo(one);
        String token = TokenUtil.sign(one.getId());
        redisService.putToken(one.getId(), token);
        redisService.delEmailFailureTimes(userEmail);
        Map<String, Object> data = new HashMap<>(3);
        data.put(ApiConstants.TOKEN,token);
        data.put("permissions", menuService.getMenuList(one.getId()));
        data.put("loginName", one.getName());
        addCookie(response,token);
        //redis写入失败次数
        //判断用户是否正确
        //将redis中取消掉
        //记录用户ip 登录信息

        return ResponseEntity.success("登录成功",data);
    }

    public boolean emailFailureTime(String userEmail) {
        String emailFailureTimes = redisService.getEmailFailureTimes(userEmail);
        if (StringUtils.isNotBlank(emailFailureTimes) && Integer.parseInt(emailFailureTimes) > ApiConstants.MAX_DEFAULT_TIMES) {
            return true;
        }
        return false;
    }

    public void putEmailFailureTime(String userEmail) {
        String emailFailureTimes = redisService.getEmailFailureTimes(userEmail);
        if(StringUtils.isBlank(emailFailureTimes)){
            redisService.putEmailFailureTimes(userEmail,"1");
        }else{
            int i = Integer.parseInt(emailFailureTimes);
            i++;
            redisService.putEmailFailureTimes(userEmail,String.valueOf(i));
        }
    }
    private void addCookie(HttpServletResponse response, String token) {
        // 将token放入cookie中，使得浏览器可以不用自己加token访问接口
        Cookie cookie = new Cookie(ApiConstants.TOKEN, token);
        // 默认cookie有效期1小时
        cookie.setMaxAge(ApiConstants.DEFAULT_COOKIE_TIMEOUT);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
