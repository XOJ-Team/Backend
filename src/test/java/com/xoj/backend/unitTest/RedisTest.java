package com.xoj.backend.unitTest;

import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.util.JacksonUtils;
import com.xoj.backend.util.RedisUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void setBasic() {
        redisUtils.set("Chen", "Yingxi");
        Assertions.assertEquals("Yingxi", redisUtils.getValue("Chen"));
        redisUtils.set("jsonStr", "");
        Assertions.assertEquals("", redisUtils.getValue("jsonStr"));
        redisUtils.delete("jsonStr");
        Assertions.assertNull(redisUtils.getValue("jsonStr"));
    }


    @Test
    public void testObject() {
        QuestionModel question = QuestionModel.builder()
                .id(1L)
                .accept(0L)
                .creator(8L)
                .questionLevel(0)
                .creatorName("Yingxi")
                .build();
        String key = "QUESTION" + question.getId();
        String JSONString = JacksonUtils.obj2String(question);
        redisUtils.set(key, JSONString);
        String str = redisUtils.getValue(key);
        if (StringUtils.hasText(str)) {
            QuestionModel questionGet = JacksonUtils.string2Obj(str, QuestionModel.class);
            if (null == questionGet) {
                return;
            }
            System.out.println(questionGet);
        }
    }
}
