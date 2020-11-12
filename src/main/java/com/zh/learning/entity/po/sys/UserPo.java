package com.zh.learning.entity.po.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
    * 
    * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="UserPo对象", description="")
public class UserPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主建ID")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户创建时间")
    private Date createTime;

}
