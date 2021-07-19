package com.zh.learning.config;

import com.zh.learning.annotation.RedisLockRequired;
import com.zh.learning.constants.RedisLockTypeEnum;
import com.zh.learning.exception.ApiException;
import com.zh.learning.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zh
 * @date 2021-07-16 9:44
 */
@Component
@Aspect
@Slf4j
public class RedisLockAop {

    @Autowired
    StringRedisTemplate template;

    /**
     * @annotation 中的路径表示拦截特定注解
     */
    @Pointcut("@annotation(com.zh.learning.annotation.RedisLockRequired)")
    public void redisLock() {
    }

    //定义环绕通知
    @Around(value = "redisLock()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        RedisLockRequired redisLockRequired = ((MethodSignature) pjp.getSignature())
                .getMethod().getDeclaredAnnotation(RedisLockRequired.class);
        //校验信息
        validatedLockInfo(redisLockRequired);

        RedisLockTypeEnum redisLockTypeEnum = redisLockRequired.typeEnum();
        String business = redisLockTypeEnum.getBusiness();
        String uniqueValue = redisLockTypeEnum.getUniqueValue(redisLockRequired.lockFiled());
        int lockTime = redisLockRequired.lockTime();
        if (Boolean.FALSE.equals(lock(business, uniqueValue, lockTime))) {
            log.info("lock failed");
            throw new ApiException("You can't do it，because another has get the lock =-=");
        }
        Thread currentThread = Thread.currentThread();
        Object proceed = pjp.proceed();
        if (currentThread.isInterrupted()) {
            unLock(business, uniqueValue);
            throw new InterruptedException("You had been interrupted =-=");
        }
        unLock(business, uniqueValue);
        log.info("release the lock, businessKey is [" + business + "]");
        return proceed;
    }

    /**
     * redis 加锁
     *
     * @param key
     * @param uniqueValue
     * @param expireTime
     * @return
     */
    public Boolean lock(String key, String uniqueValue, int expireTime) {
        Boolean aBoolean = template.opsForValue().setIfAbsent(key, uniqueValue, expireTime, TimeUnit.SECONDS);
        if (Objects.isNull(aBoolean)) {
            return false;
        }
        return aBoolean;
    }

    public boolean unLock(String key, String uniqueValue) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = RedisScript.of(luaScript, Long.class);
        Long result = template.execute(redisScript, Collections.singletonList(key), uniqueValue);
        return Long.valueOf(1).equals(result);
    }

    public void validatedLockInfo(RedisLockRequired redisLockRequired) {
        AssertUtils.nonNull(redisLockRequired, "redis注解为空");
        RedisLockTypeEnum redisLockTypeEnum = redisLockRequired.typeEnum();
        int lockTime = redisLockRequired.lockTime();
        AssertUtils.nonNull(lockTime, "lockTime为空");
        AssertUtils.nonNull(redisLockTypeEnum, "业务类型为空");
    }

}
