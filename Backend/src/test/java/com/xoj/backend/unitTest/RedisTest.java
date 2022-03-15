package com.xoj.backend.unitTest;

import com.xoj.backend.util.RedisUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void setKey() {
        redisUtils.set("123", "456");
    }

    @Test
    public void testGetKey() {
        System.out.println(redisUtils.getValue("123"));
    }

    @Test
    public void testDelete(){
        redisUtils.delete("jsonStr");
    }
}
