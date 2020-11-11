package com.zh.learning.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zh
 * @version 1.0
 * @date 2020/10/22 17:35
 */
@Data
public class User {
    @ApiModelProperty(name = "id",value = "id")
    @Size(max= 3,message = "id不能超过3")
    String id;
    @ApiModelProperty(name = "name",value = "名字")
    @NotBlank(message = "name不能为空")
    String name;
}
