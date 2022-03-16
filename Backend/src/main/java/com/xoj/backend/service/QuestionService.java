package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.entity.Question;

public interface QuestionService {
    void create(QuestionCreateDto dto);

    void modify(QuestionModifyDto dto);

    Question selectOneQuestion(Long id);

    PageInfo<Question> selectQuestions(QuestionPageDto dto);

    PageInfo<Question> selectAllQuestions(QuestionPageDto dto);

    PageInfo<Question> selectAllShowQuestions(QuestionPageDto dto);

    void delete(Long id);

    void hide(Long id);

    void show(Long id);
}
