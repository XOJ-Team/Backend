package com.xoj.backend.service;

import com.xoj.backend.dto.QuestionCompetitionCreateDto;
import com.xoj.backend.dto.QuestionCompetitionModifyDto;
import com.xoj.backend.model.QuestionModel;

import java.util.List;

/**
 * @author 1iin
 */
public interface QuestionCompetitionService {
    void create(QuestionCompetitionCreateDto dto);

    void modify(QuestionCompetitionModifyDto dto);

    void delete(Long id);

    List<QuestionModel> selectQuestionsByCompetition(Long competitionId);
}
