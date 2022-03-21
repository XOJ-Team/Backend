package com.xoj.backend.service;

import com.xoj.backend.dto.TestcaseQuestionCreateDto;
import com.xoj.backend.dto.TestcaseQuestionModifyDto;
import com.xoj.backend.entity.TestcaseQuestion;

import java.util.List;

public interface TestcaseQuestionService {
    void create(TestcaseQuestionCreateDto dto);

    void modify(TestcaseQuestionModifyDto dto);

    void delete(Long id);

    List<TestcaseQuestion> testcases(Long questionId);
}
