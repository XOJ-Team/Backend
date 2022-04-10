package com.xoj.backend.service.impl;

import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.dto.CompetitionModifyDto;
import com.xoj.backend.entity.Competition;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.mapper.CompetitionMapper;
import com.xoj.backend.model.CompetitionModel;
import com.xoj.backend.service.CompetitionService;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.util.JacksonUtils;
import com.xoj.backend.util.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author 1iin
 */
@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final UserBaseService userBaseService;

    private final CompetitionMapper mapper;

    private RedisUtils redisUtils;

    private final String prefix = "COMPETITION";

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

    /**
     * modify the introduction and name of a competition
     *
     * @param dto
     */
    @Override
    public void modify(CompetitionModifyDto dto) {
        String key = prefix + dto.getId();
        if (redisUtils.hasKey(key)) {
            redisUtils.delete(key);
        }
        UserBase user = userBaseService.getCurrentUser();
        Competition competition = Competition.builder()
                .name(dto.getName())
                .briefIntroduction(dto.getBriefIntroduction())
                .creator(user.getId())
                .creatorName(user.getName())
                .build();
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(competition, example);
    }

    /**
     * show one competition
     *
     * @param id
     */
    @Override
    public CompetitionModel selectOneCompetition(Long id) {
        try {
            String key = prefix + id;
            String str = null;
            if (redisUtils.hasKey(key)) {
                str = redisUtils.getValue(key);
            }
            if (StringUtils.hasText(str)) {
                return JacksonUtils.string2Obj(str, CompetitionModel.class);
            } else {
                CompetitionModel competition = mapper.selectCompetition(id);
                String JSONString = JacksonUtils.obj2String(competition);
                redisUtils.set(key, JSONString);
                return competition;
            }
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    /**
     * delete a competition
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        String key = prefix + id;
        if (redisUtils.hasKey(key)) {
            redisUtils.delete(key);
        }
        Example example = new Example(Competition.class);
        example.createCriteria().andEqualTo("id", id);
        Competition competition = Competition.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .deleteTime(new Date())
                .build();
        mapper.updateByExampleSelective(competition, example);
    }
}
