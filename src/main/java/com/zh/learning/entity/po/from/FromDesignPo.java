package com.zh.learning.entity.po.from;

import lombok.Data;

/**
 * @author zh
 * @date 2021-07-20 13:53
 */
@Data
public class FromDesignPo extends LongBaseModel {

    private String name;
    private Integer status;
    private Integer category;
    private String description;
    private Boolean canModify;
}
