package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.dto.UserCompetitionCreateDto;
import com.xoj.backend.dto.UserCompetitionPageDto;
import com.xoj.backend.entity.UserCompetition;

import java.util.Date;

/**
 * @author 1iin
 */
public interface UserCompetitionService {
    void create(UserCompetitionCreateDto dto);

    PageInfo<UserCompetition> selectUserCompetition(UserCompetitionPageDto dto,Long competitionId);

    void deleteUserCompetition(Long competitionId);

    boolean isRegister(Long userId, Long competitionId);

    void updateScoreAndPenalty(Long userId, Long competitionId);

    void updateWrong(Long userId, Long competitionId);
}
