package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.dto.CompetitionModifyDto;
import com.xoj.backend.dto.CompetitionPageDto;
import com.xoj.backend.model.CompetitionDetailModel;
import com.xoj.backend.model.CompetitionModel;

import java.text.SimpleDateFormat;

/**
 * @author 1iin
 */
public interface CompetitionService {
    void create(CompetitionCreateDto dto);

    void modify(CompetitionModifyDto dto);

    CompetitionDetailModel selectOneCompetition(Long id);

    void delete(Long id);

    PageInfo<CompetitionModel> selectCompetitions(CompetitionPageDto dto);
}
