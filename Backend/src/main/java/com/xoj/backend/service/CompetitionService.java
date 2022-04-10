package com.xoj.backend.service;

import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.dto.CompetitionModifyDto;
import com.xoj.backend.model.CompetitionModel;

/**
 * @author 1iin
 */
public interface CompetitionService {
    void create(CompetitionCreateDto dto);

    void modify(CompetitionModifyDto dto);

    CompetitionModel selectOneCompetition(Long id);

    void delete(Long id);

}
