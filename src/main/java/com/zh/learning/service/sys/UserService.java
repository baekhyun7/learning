package com.zh.learning.service.sys;

import com.zh.learning.entity.po.sys.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
public interface UserService extends IService<UserPo> {

    /**
     * 总结
     * 1、在没加事务的方法a调用加了事务的方法b  方法b的事务完全不会起作用
     * 2、在加了事务的方法a调用没加事务的方法b   方法a的事务会蔓延到方法b上
     * 3、在加了事务的方法a调用方法b  如果try catch b的异常 没抛出异常  事务会失效 抛出了则不会失效
     */
    /**
     * 新增用户 - 事务测试
     */
    void addUser1() throws Exception;

    /**
     * 新增用户 - 事务测试
     */
    void addUser2() throws Exception;

}
