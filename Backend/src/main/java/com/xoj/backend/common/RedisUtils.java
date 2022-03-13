package com.xoj.backend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set key
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * delete the key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * determine the key is or not exist
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public void deleteKey(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    /**
     * get the according value
     * @param key
     * @return
     */
    public String getValue(String key) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
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
}
