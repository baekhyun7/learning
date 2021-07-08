package com.zh.learning.vo.request;

import com.zh.learning.vo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zh
 * @date 2021/7/8
 */
@Data
public class ElasticSearchAddIndexReq {

    @ApiModelProperty(value = "indexName", name = "索引名称")
    private String indexName;
    @ApiModelProperty(value = "user", name = "用户数据")
    private User user;
}
