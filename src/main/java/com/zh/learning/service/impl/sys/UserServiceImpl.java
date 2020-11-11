package com.zh.learning.service.impl.sys;

import com.zh.learning.entity.po.sys.UserPo;
import com.zh.learning.dao.sys.UserDao;
import com.zh.learning.service.sys.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserPo> implements UserService {

}
