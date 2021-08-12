package com.zh.learning.dao.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.learning.entity.po.sys.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
public interface UserDao extends BaseMapper<UserPo> {


    /**
     * 测试分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<UserPo> getPageInfo(Page page, @Param(Constants.WRAPPER) Wrapper<UserPo> wrapper);

}
