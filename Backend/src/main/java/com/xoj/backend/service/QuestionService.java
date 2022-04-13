package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.model.QuestionModel;

public interface QuestionService {
    void create(QuestionCreateDto dto);

    void modify(QuestionModifyDto dto);

    QuestionModel selectOneQuestion(Long id);

    PageInfo<QuestionModel> selectQuestions(QuestionPageDto dto);

    PageInfo<QuestionModel> selectAllQuestions(QuestionPageDto dto);

    PageInfo<QuestionModel> selectAllShowQuestions(QuestionPageDto dto);

    void delete(Long id);

    void hide(Long id);

    void show(Long id);

    void calRate(Long id, Integer result);
}
