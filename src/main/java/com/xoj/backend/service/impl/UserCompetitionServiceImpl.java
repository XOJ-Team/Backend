package com.xoj.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.UserCompetitionCreateDto;
import com.xoj.backend.dto.UserCompetitionPageDto;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.entity.UserCompetition;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.mapper.CompetitionMapper;
import com.xoj.backend.mapper.UserCompetitionMapper;
import com.xoj.backend.model.CompetitionModel;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.service.UserCompetitionService;
import com.xoj.backend.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 1iin
 */
@Service
@AllArgsConstructor
public class UserCompetitionServiceImpl implements UserCompetitionService {
    private UserCompetitionMapper mapper;

    private final UserBaseService userBaseService;

    private CompetitionMapper competitionMapper;

    /**
     * user register a competition
     *
     * @param dto
     */
    @Override
    public void create(UserCompetitionCreateDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        CompetitionModel competitionModel = competitionMapper.selectCompetition(dto.getCompetitionId());
        if (null == competitionModel) {
            throw new BizException();
        }
        if (DateUtils.string2Date(competitionModel.getEndTime()).before(new Date())) {
            throw new BizException();
        }
        Example example = new Example(UserCompetition.class);
        example.createCriteria()
                .andEqualTo("userId", user.getId())
                .andEqualTo("competitionId", dto.getCompetitionId());
        UserCompetition userCompetition = UserCompetition.builder()
                .userId(user.getId())
                .competitionId(dto.getCompetitionId())
                .score(dto.getScore())
                .penalty(dto.getPenalty())
                .wrong(dto.getWrong())
                .isDelete(CommonConstants.NOT_DELETED)
                .build();
        if (null != mapper.selectOneByExample(example)) {
            mapper.updateByExampleSelective(userCompetition, example);
        } else {
            mapper.insertSelective(userCompetition);
        }
    }

    /**
     * user ranking list in a competition
     *
     * @param dto
     */
    @Override
    public PageInfo<UserCompetition> selectUserCompetition(UserCompetitionPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<UserCompetition> competitions = mapper.selectUserCompetition();
        return PageInfo.of(competitions);
    }

    @Override
    public void deleteUserCompetition(Long competitionId) {
        CompetitionModel competitionModel = competitionMapper.selectCompetition(competitionId);
        if (null == competitionModel) {
            throw new BizException();
        }
        if (DateUtils.string2Date(competitionModel.getStartTime()).before(new Date())) {
            throw new BizException();
        }
        UserBase user = userBaseService.getCurrentUser();
        Example example = new Example(UserCompetition.class);
        example.createCriteria()
                .andEqualTo("userId", user.getId())
                .andEqualTo("competitionId", competitionId)
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED);
        UserCompetition userCompetition = UserCompetition.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .build();
        mapper.updateByExampleSelective(userCompetition, example);
    }

    @Override
    public boolean isRegister(Long userId, Long competitionId) {
        Example example = new Example(UserCompetition.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("competitionId", competitionId);
        UserCompetition userCompetition = mapper.selectOneByExample(example);
        return userCompetition != null;
    }

    @Override
    public void updateScoreAndPenalty(Long userId, Long competitionId) {
        Example example = new Example(UserCompetition.class);
        CompetitionModel competitionModel = competitionMapper.selectCompetition(competitionId);

        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("competitionId", competitionId);
        UserCompetition userCompetition = mapper.selectOneByExample(example);
        userCompetition.setScore(userCompetition.getScore() + 1);
        userCompetition.setPenalty((int) (System.currentTimeMillis() / 1000 - DateUtils.string2Date(competitionModel.getStartTime()).getTime() / 1000));

        mapper.updateByExampleSelective(userCompetition, example);
    }


    @Override
    public void updateWrong(Long userId, Long competitionId) {
        Example example = new Example(UserCompetition.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("competitionId", competitionId);
        UserCompetition userCompetition = mapper.selectOneByExample(example);
        userCompetition.setWrong(userCompetition.getWrong() + 1);
        mapper.updateByExampleSelective(userCompetition, example);
    }


}
