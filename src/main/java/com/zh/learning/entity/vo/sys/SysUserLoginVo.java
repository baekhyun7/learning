package com.zh.learning.entity.vo.sys;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author zh
 */
@Data
public class SysUserLoginVo {
    @Size(min = 1, max = 255, message = "用户长度错误")
    String userEmail;

    @Size(min = 1, max = 64, message = "密码长度错误" )
    String password;

//    @Size(min = 5, max = 10, message = "验证码长度错误")
//    String code;
}
