package com.zh.learning.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.learning.entity.po.sys.MenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zh
 * @since 2020-11-11
 */
@Mapper
@Repository
public interface MenuDao extends BaseMapper<MenuPo> {


    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<MenuPo> getMenuList(@Param("userId") String userId);
}
