package com.xoj.backend.unitTest;

import com.xoj.backend.common.UserThreadLocal;
import com.xoj.backend.entity.User;
import com.xoj.backend.service.UserBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class UserTest {
    @Autowired
    private UserBaseService userBaseService;

    @Test
    public void testUser(){
        User user = User.builder()
                .name("Yingxi")
                .id(1927885L)
                .mail("yingxi.chen@outlook.com")
                .build();
        UserThreadLocal.set(user);
        System.out.println(userBaseService.getCurrentUser());
    }
}
