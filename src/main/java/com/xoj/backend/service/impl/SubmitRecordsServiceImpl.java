package com.xoj.backend.service.impl;

import com.xoj.backend.common.ResultEnum;
import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.mapper.SubmitRecordsMapper;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.model.SubmitRecordsModel;
import com.xoj.backend.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Yingxi
 */
@Service
@AllArgsConstructor
public class SubmitRecordsServiceImpl implements SubmitRecordsService {

    private final SubmitRecordsMapper mapper;

    private final UserBaseService userBaseService;

    private final UserInfoService userInfoService;

    private final QuestionService questionService;

    private final QuestionCompetitionService questionCompetitionService;

    private final UserCompetitionService userCompetitionService;

    /**
     * create submit records
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRecord(SubmitRecordsCreateDto dto) {
        UserBase user = userBaseService.getCurrentUser();
        SubmitRecords record = SubmitRecords.builder()
                .questionId(dto.getQuestionId())
                .questionName(dto.getQuestionName())
                .lang(dto.getLang())
                .result(dto.getResult())
                .memoryCost(dto.getMemoryCost())
                .timeCost(dto.getTimeCost())
                .userId(user.getId())
                .codes(dto.getCodes()).build();
        QuestionModel questionModel = questionService.selectOneQuestion(dto.getQuestionId());
        // if (a common user submit the particular question answer during the competition)
        if (user.getAuthority() < 3 && questionModel.getIsHide()) {
            Long competitionId = questionCompetitionService.getCompetitionId(dto.getQuestionId());
            if (null != competitionId) {
                record.setCompetitionId(competitionId);
            } else {
                throw new BizException(CommonErrorType.COMPETITION_NOT_FOUND);
            }
            if (!userCompetitionService.isRegister(user.getId(), competitionId)) {
                throw new BizException(CommonErrorType.UNREGISTERED_COMPETITION);
            }
            if (ResultEnum.ACCEPTED.getCode().equals(dto.getResult())) {
                userCompetitionService.updateScoreAndPenalty(user.getId(), competitionId);
            } else {
                userCompetitionService.updateWrong(user.getId(), competitionId);
            }
        }
        // common submit process
        if (ResultEnum.ACCEPTED.getCode().equals(dto.getResult()) && determine(dto.getQuestionId(), user.getId())){
            modifyUser(dto.getQuestionId(), user);
        }
        mapper.insertSelective(record);
        questionService.calRate(dto.getQuestionId(), dto.getResult());
    }

    /**
     * determine whether the user has done this question
     * @param questionId
     * @param userId
     * @return
     */
    private boolean determine(Long questionId, Long userId) {
        Example example = new Example(SubmitRecords.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("questionId", questionId)
                .andEqualTo("result", ResultEnum.ACCEPTED.getCode());
        List<SubmitRecords> submitRecords = mapper.selectByExample(example);
        return CollectionUtils.isEmpty(submitRecords);
    }

    /**
     * modify solved questions
     * @param questionId
     * @param user
     */
    private void modifyUser(Long questionId, UserBase user){
        userInfoService.updateSolvedQuestions(questionId, user);
    }

    /**
     * return submit records of a user in this question
     * @param questionId
     * @return
     */
    @Override
    public List<SubmitRecordsModel> selectQuestionRecords(Long questionId) {
        UserBase user = userBaseService.getCurrentUser();
        return mapper.selectQuestionRecords(questionId, user.getId());
    }

    /**
     * return submit records of a user
     * @return
     */
    @Override
    public List<SubmitRecordsModel> selectUserRecords(Long userId) {
        return mapper.selectUserRecords(userId);
    }

    /**
     * update comment of a record
     * @param dto
     */
    @Override
    public void modifyRecord(SubmitRecordsModifyDto dto) {
        SubmitRecords record = SubmitRecords.builder().comments(dto.getComments()).build();
        Example example = new Example(SubmitRecords.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(record, example);
    }

    /**
     * select solved questions list
     * @param userId
     * @return
     */
    @Override
    public List<Long> solved(Long userId) {
        List<Long> solved = mapper.selectQuestionIds(userId);
        return solved;
    }

    @Override
    public SubmitRecordsModel selectRecord(Long id) {
        SubmitRecordsModel record = mapper.selectOneRecord(id);
        if (null == record) {
            throw new BizException(CommonErrorType.SUBMIT_RECORD_NOT_FOUND);
        }
        return record;
    }
}
