package com.zh.learning.vo.request;

import com.zh.learning.entity.po.sys.LogPo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zh
 * @date 2021/7/8
 */
@Data
public class ElasticSearchLogAddIndexReq extends ElasticSearchReq {

    @ApiModelProperty(value = "logPo", name = "用户数据")
    private LogPo logPo;
}
