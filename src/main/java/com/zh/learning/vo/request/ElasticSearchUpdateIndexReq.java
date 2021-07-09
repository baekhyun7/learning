package com.zh.learning.vo.request;

import com.zh.learning.vo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zh
 * @date 2021/7/9
 */
@Data
public class ElasticSearchUpdateIndexReq extends ElasticSearchReq{

    @ApiModelProperty(value = "user", name = "用户数据")
    private User user;
}
