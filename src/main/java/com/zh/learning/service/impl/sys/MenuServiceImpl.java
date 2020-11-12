package com.zh.learning.service.impl.sys;

import com.zh.learning.dao.sys.MenuDao;
import com.zh.learning.entity.po.sys.MenuPo;
import com.zh.learning.service.sys.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zh
 * @since 2020-11-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuPo> implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<MenuPo> getMenuList(String userId) {
        return menuDao.getMenuList(userId);
    }
}
