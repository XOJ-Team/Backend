package com.xoj.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.UserCompetitionCreateDto;
import com.xoj.backend.dto.UserCompetitionPageDto;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.entity.UserCompetition;
import com.xoj.backend.mapper.UserCompetitionMapper;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.service.UserCompetitionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 1iin
 */
@Service
@AllArgsConstructor
public class UserCompetitionServiceImpl implements UserCompetitionService {
    private UserCompetitionMapper mapper;

    private final UserBaseService userBaseService;

    /**
     * user register a competition
     *
     * @param dto
     */
    @Override
    public void create(UserCompetitionCreateDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        Example example = new Example(UserCompetition.class);
        example.createCriteria()
                .andEqualTo("userId", user.getId())
                .andEqualTo("competitionId", dto.getCompetitionId());
        UserCompetition userCompetition = UserCompetition.builder()
                .userId(user.getId())
                .competitionId(dto.getCompetitionId())
                .score(dto.getScore())
                .penalty(dto.getPenalty())
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
}
