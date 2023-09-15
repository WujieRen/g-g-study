package cn.rwj.study.spring.springbasic.idempotence.api.common;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author rwj
 * @since 2023/9/15
 */
@Component
public class RedisIdempotentStorage implements IdempotentStorage{

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(String idempotentId) {
        redisTemplate.opsForValue().set(idempotentId, idempotentId, 10, TimeUnit.MINUTES);
    }

    @Override
    public boolean delete(String idempotentId) {
        return redisTemplate.delete(idempotentId);
    }

}
