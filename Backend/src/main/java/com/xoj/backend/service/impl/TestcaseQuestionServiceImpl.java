package com.xoj.backend.service.impl;

import com.xoj.backend.dto.TestcaseCreateDto;
import com.xoj.backend.entity.TestcaseQuestion;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.mapper.TestcaseQuestionMapper;
import com.xoj.backend.service.TestcaseQuestionService;
import com.xoj.backend.service.UserBaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestcaseQuestionServiceImpl implements TestcaseQuestionService {

    private final UserBaseService userBaseService;

    private final TestcaseQuestionMapper mapper;

    @Override
    public void create(TestcaseCreateDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        TestcaseQuestion testcaseQuestion = TestcaseQuestion.builder()
                .questionId(dto.getQuestionId())
                .testcase(dto.getTestcase())
                .result(dto.getResult())
                .creator(user.getId())
                .creatorName(user.getName())
                .build();
        mapper.insertSelective(testcaseQuestion);
    }
}
