package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.dto.UserCompetitionCreateDto;
import com.xoj.backend.dto.UserCompetitionPageDto;
import com.xoj.backend.entity.UserCompetition;

/**
 * @author 1iin
 */
public interface UserCompetitionService {
    void create(UserCompetitionCreateDto dto);

    PageInfo<UserCompetition> selectUserCompetition(UserCompetitionPageDto dto);

    void deleteUserCompetition(Long competitionId);
}
