package com.zh.learning.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zh
 * @date 2021/7/9
 */
@Data
public class ElasticSearchReq {
    @ApiModelProperty(value = "indexName", name = "索引名称")
    private String indexName;
    @ApiModelProperty(value = "id", name = "id")
    private String id;
}
