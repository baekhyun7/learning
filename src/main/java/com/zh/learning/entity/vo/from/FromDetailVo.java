package com.zh.learning.entity.vo.from;

import com.zh.learning.entity.po.from.FromPropertiesPo;
import com.zh.learning.entity.po.from.LongBaseModel;
import lombok.Data;

import java.util.List;

/**
 * @author zh
 * @date 2021-07-20 14:07
 */
@Data
public class FromDetailVo extends LongBaseModel {
    private Boolean isContainer;
    private String name;
    private List<FromPropertiesPo> content;
}
