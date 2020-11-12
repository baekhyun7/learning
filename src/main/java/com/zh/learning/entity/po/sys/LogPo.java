package com.zh.learning.entity.po.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
@ApiModel(value="LogPo对象", description="")
public class LogPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作者id")
    private String userId;

    @ApiModelProperty(value = "操作者名称")
    private String userName;

    @ApiModelProperty(value = "操作者email")
    private String userEmail;

    @ApiModelProperty(value = "菜单")
    private String menu;

    @ApiModelProperty(value = "请求的资源")
    private String uri;

    @ApiModelProperty(value = "操作")
    private String operation;

    @ApiModelProperty(value = "结果信息")
    private String msg;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
