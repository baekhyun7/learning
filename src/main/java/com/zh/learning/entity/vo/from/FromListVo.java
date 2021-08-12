package com.zh.learning.entity.vo.from;

import com.zh.learning.entity.po.from.LongBaseModel;
import lombok.Data;

/**
 * @author zh
 * @date 2021-07-20 14:07
 */
@Data
public class FromListVo extends LongBaseModel {

    private String name;

    private String categoryStr;

    private String statusStr;

    private String author;

    private Integer procedureNum;
}
