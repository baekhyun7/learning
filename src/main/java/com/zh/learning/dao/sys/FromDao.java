package com.zh.learning.dao.sys;

import com.zh.learning.entity.po.from.FromPropertiesPo;
import com.zh.learning.entity.vo.from.FromDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Mapper
@Repository
public interface FromDao {


    /**
     * 查询表单属性值
     * @param fromId
     * @return
     */
    List<FromPropertiesPo> getByFromId(Long fromId);

}
