package com.xoj.backend.unitTest;

import com.xoj.backend.mapper.CompetitionMapper;
import com.xoj.backend.mapper.UserCompetitionMapper;
import com.xoj.backend.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 1iin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class UserCompetitionTest {
    @Autowired
    private UserCompetitionMapper mapper;

//    @Test
//    public void test(){
//        System.out.println(mapper.selectUserCompetition());
//    }
}
