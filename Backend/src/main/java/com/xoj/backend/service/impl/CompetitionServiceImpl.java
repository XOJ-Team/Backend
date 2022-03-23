package com.xoj.backend.service.impl;

import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.entity.Competition;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.mapper.CompetitionMapper;
import com.xoj.backend.service.CompetitionService;
import com.xoj.backend.service.UserBaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 1iin
 */
@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final UserBaseService userBaseService;

    private final CompetitionMapper mapper;

    /**
     * create a competition
     *
     * @param dto
     */
    @Override
    public void create(CompetitionCreateDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        Competition competition = Competition.builder()
                .name(dto.getName())
                .briefIntroduction(dto.getBriefIntroduction())
                .creator(user.getId())
                .creatorName(user.getName())
                .build();
        mapper.insertSelective(competition);
    }
}
