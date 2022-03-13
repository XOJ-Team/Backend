package com.xoj.backend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    //    判断key是否存在
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    //    根据key获取过期时间
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    //    删除多个key
    public void deleteKey(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    public String getString(String key) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.opsForValue().set("jsonStr", "{'userName':'xxf','age':20}");
            System.out.println(redisTemplate.opsForValue().get(key));
            return (String) redisTemplate.opsForValue().get(key);
        } else {
            return null;
        }
    }

    public Boolean setWithExpire(String key, Object value, int expireTime) {
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

}
