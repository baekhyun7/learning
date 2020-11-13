package com.zh.learning.entity.vo.sys;

import com.zh.learning.constants.RegxpConstants;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author zh
 */
@Data
public class SysUserRegisterVo {
    @Size(min = 1, max = 255, message = "用户名错误")
    String name;

    @Size(min = 1, max = 255, message = "用户长度错误")
    @Pattern(regexp = RegxpConstants.IS_MAIL,message = "用户格式错误")
    String userEmail;

    @Size(min = 1, max = 64, message = "密码长度错误" )
    String password;

//    @Size(min = 5, max = 10, message = "验证码长度错误")
//    String code;
}
