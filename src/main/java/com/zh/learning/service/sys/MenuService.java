package com.zh.learning.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.learning.entity.po.sys.MenuPo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zh
 * @since 2020-11-11
 */
public interface MenuService extends IService<MenuPo> {

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<MenuPo> getMenuList(String userId);

}
