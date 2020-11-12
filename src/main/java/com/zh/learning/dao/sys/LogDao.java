package com.zh.learning.dao.sys;

import com.zh.learning.entity.po.sys.LogPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zh
 * @since 2020-11-12
 */
@Mapper
@Repository
public interface LogDao extends BaseMapper<LogPo> {

}
