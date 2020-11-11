package com.zh.learning.service;

import com.zh.learning.entity.ApiConstants;
import com.zh.learning.entity.po.sys.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2020-11-02
 */
@Service
public class RedisService {

    @Autowired
    StringRedisTemplate template;

    public String getToken(String userId) {
        return valueGet(userId+"-"+ ApiConstants.TOKEN);
    }

    /**
     * 登录成功后，生成token，并放验证码到redis服务器
     */
    public String putToken(String userId, String token) {
        valueSet(userId + "_" + ApiConstants.TOKEN,
                token, ApiConstants.DEFAULT_TOKEN_TIMEOUT,
                TimeUnit.SECONDS);
        return token;
    }

    /**
     * 登录成功后，将用户名和邮箱存入redis
     */
    public void putUserInfo(UserPo user) {
        valueSet(user.getId() + "_" + ApiConstants.NAME,
                user.getName(),
                ApiConstants.DEFAULT_TOKEN_TIMEOUT,
                TimeUnit.SECONDS);
        valueSet(user.getId() + "_" + ApiConstants.EMAIL,
                user.getEmail(),
                ApiConstants.DEFAULT_TOKEN_TIMEOUT,
                TimeUnit.SECONDS);
    }

    /**
     * 验证时，根据用户名，获取redis里面的用户信息，姓名和邮箱
     *
     * @return sysusr
     */
    public UserPo getUserInfo(String userId) {
        UserPo user = new UserPo();
        user.setId(userId);
        user.setName(valueGet(userId + "_" + ApiConstants.NAME));
        user.setEmail(valueGet(userId + "_" + ApiConstants.EMAIL));
        return user;
    }

    /**
     * 设置缓存
     *
     * @param key      键
     * @param value    值
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     */
    public void valueSet(String key, String value, long timeout, TimeUnit timeUnit) {
        template.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public String valueGet(String key) {
        return template.opsForValue().get(key);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return Boolean
     */
    public Boolean delete(String key) {
        return template.delete(key);
    }

    /**
     * 确认缓存是否存在
     *
     * @param key 键
     * @return Boolean
     */
    public Boolean hasKey(String key) {
        return template.hasKey(key);
    }

    /**
     * 获取通过此键存储的list的长度
     *
     * @param key 键
     * @return Long
     */
    public Long listSize(String key) {
        return template.opsForList().size(key);
    }

    /**
     * 重置一个根据此键存储的list的存储内容
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置
     */
    public void listTrim(String key, long start, long end) {
        template.opsForList().trim(key, start, end);
    }

    /**
     * 将一个根据此键存储的list左弹出
     *
     * @param key 键
     * @return 值
     */
    public String listLeftpop(String key) {
        return template.opsForList().leftPop(key);
    }

    /**
     * 将一个根据此键存储的list从右边开始添加数据
     *
     * @param key     键
     * @param strings 值
     * @return when used in pipeline / transaction
     */
    public Long listRightPushAll(String key, String... strings) {
        return template.opsForList().rightPushAll(key, strings);
    }
}
