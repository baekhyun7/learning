package com.zh.learning.dao.sys;

import com.zh.learning.entity.po.sys.UserPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Mapper
public interface UserDao extends BaseMapper<UserPo> {

}
