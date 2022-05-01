package com.xoj.backend.service.impl;

import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.TestcaseQuestionCreateDto;
import com.xoj.backend.dto.TestcaseQuestionModifyDto;
import com.xoj.backend.entity.TestcaseQuestion;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.mapper.TestcaseQuestionMapper;
import com.xoj.backend.service.TestcaseQuestionService;
import com.xoj.backend.service.UserBaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Yingxi
 */

@Service
@AllArgsConstructor
public class TestcaseQuestionServiceImpl implements TestcaseQuestionService {

    private final UserBaseService userBaseService;

    private final TestcaseQuestionMapper mapper;

    /**
     * create a testcase
     * @param dto
     */
    @Override
    public void create(TestcaseQuestionCreateDto dto) {
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

    /**
     * modify a testcase
     * @param dto
     */
    @Override
    public void modify(TestcaseQuestionModifyDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        TestcaseQuestion testcase = TestcaseQuestion.builder()
                .testcase(dto.getTestcase())
                .result(dto.getResult())
                .modifier(user.getId())
                .modifierName(user.getName()).build();
        Example example = new Example(TestcaseQuestion.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(testcase, example);
    }

    /**
     * delete a testcase
     * @param id
     */
    @Override
    public void delete(Long id) {
        Example example = new Example(TestcaseQuestion.class);
        example.createCriteria().andEqualTo("id", id);
        TestcaseQuestion testcaseQuestion = TestcaseQuestion.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(testcaseQuestion, example);
    }

    /**
     * show all the testcases of one question
     * @param questionId
     * @return
     */
    @Override
    public List<TestcaseQuestion> testcases(Long questionId) {
        Example example = new Example(TestcaseQuestion.class);
        example.createCriteria()
                .andEqualTo("questionId", questionId)
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED);
        return mapper.selectByExample(example);
    }

}
