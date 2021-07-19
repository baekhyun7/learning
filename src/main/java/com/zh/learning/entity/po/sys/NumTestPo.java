package com.zh.learning.entity.po.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author zh
 * @date 2021-07-13 11:19
 **/
@Data
@TableName("num_test")
public class NumTestPo implements Serializable {
    /**
     * id
     */
    private Integer id;

    private Integer num;

    private static final long serialVersionUID = 1L;
}