package com.zh.learning.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.learning.entity.po.sys.NumTestPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zh
 * @date 2021-07-13 11:20
 **/
@Repository
@Mapper
public interface NumTestDao extends BaseMapper<NumTestPo> {

}