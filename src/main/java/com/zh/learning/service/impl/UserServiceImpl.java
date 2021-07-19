package com.zh.learning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.learning.entity.po.sys.UserPo;
import com.zh.learning.dao.sys.UserDao;
import com.zh.learning.service.sys.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserPo> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser1() throws Exception {
        UserPo userPo = new UserPo();
        userPo.setId("1");
        userPo.setEmail("1");
        userPo.setName("1");
        userPo.setPassword("1");
        userDao.insert(userPo);
        try {
            addUser2();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new Exception("aaaa");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser2() throws Exception {
        UserPo userPo = new UserPo();
        userPo.setId("2");
        userPo.setEmail("2");
        userPo.setName("2");
        userPo.setPassword("2");
        userDao.insert(userPo);
        throw new RuntimeException("2方法抛出异常");
    }

    @Override
    public List<UserPo> getPageList(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        IPage<UserPo> pageInfo = userDao.getPageInfo(page, wrapper);
        return pageInfo.getRecords();
    }
}
