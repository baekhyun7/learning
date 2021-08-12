package com.zh.learning.service;

import com.zh.learning.entity.po.from.FromPropertiesPo;
import com.zh.learning.entity.vo.from.FromDetailVo;

import java.util.List;

/**
 * @author zh
 * @date 2021-07-20 15:20
 */
public interface FromService {

    /**
     * 获取信息
     * @param fromId
     * @return
     */
    List<FromPropertiesPo> getInfo(Long fromId);

    /**
     * 新增
     * @param data
     */
    void add(List<FromPropertiesPo> data);
}
