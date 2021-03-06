package com.xoj.backend.service.impl;

import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.dto.QuestionCompetitionCreateDto;
import com.xoj.backend.dto.QuestionCompetitionModifyDto;
import com.xoj.backend.entity.QuestionCompetition;
import com.xoj.backend.mapper.QuestionCompetitionMapper;
import com.xoj.backend.service.QuestionCompetitionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 1iin
 */
@Service
@AllArgsConstructor
public class QuestionCompetitionServiceImpl implements QuestionCompetitionService {

    private final QuestionCompetitionMapper mapper;


    /**
     * link a question to a competition
     *
     * @param dto
     */
    @Override
    public void create(QuestionCompetitionCreateDto dto) {
        Example example = new Example(QuestionCompetition.class);
        example.createCriteria()
                .andEqualTo("questionId", dto.getQuestionId());
        QuestionCompetition questionCompetition = QuestionCompetition.builder()
                .questionId(dto.getQuestionId())
                .competitionId(dto.getCompetitionId())
                .questionName(dto.getQuestionName())
                .score(dto.getScore())
                .isDelete(CommonConstants.NOT_DELETED)
                .build();
        if (null != mapper.selectOneByExample(example)) {
            mapper.updateByExampleSelective(questionCompetition, example);
        } else {
            mapper.insertSelective(questionCompetition);
        }
    }

    /**
     * modify a record of the relationship between a question and a competition
     *
     * @param dto
     */
    @Override
    public void modify(QuestionCompetitionModifyDto dto) {
        QuestionCompetition questionCompetition = QuestionCompetition.builder()
                .score(dto.getScore())
                .build();
        Example example = new Example(QuestionCompetition.class);
        example.createCriteria().andEqualTo("id", dto.getId());
        mapper.updateByExampleSelective(questionCompetition, example);
    }

    /**
     * delete a record of the relationship between a question and a competition
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        QuestionCompetition questionCompetition = QuestionCompetition.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .build();
        Example example = new Example(QuestionCompetition.class);
        example.createCriteria().andEqualTo("id", id);
        mapper.updateByExampleSelective(questionCompetition, example);
    }

    /**
     * show all the questions of one competition
     *
     * @param competitionId
     * @return
     */
    @Override
    public List<QuestionCompetition> selectQuestionsByCompetition(Long competitionId) {
        Example example = new Example(QuestionCompetition.class);
        example.createCriteria()
                .andEqualTo("competitionId", competitionId)
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED);
        List<QuestionCompetition> links = mapper.selectByExample(example);
        return links;
    }

    @Override
    public void deleteListByQuestion(Long questionId) {
        Example example = new Example(QuestionCompetition.class);
        example.createCriteria()
                .andEqualTo("questionId", questionId)
                .andEqualTo("isDelete", CommonConstants.NOT_DELETED);
        QuestionCompetition questionCompetition = QuestionCompetition.builder()
                .isDelete(CommonConstants.IS_DELETED)
                .build();
        mapper.updateByExampleSelective(questionCompetition, example);
    }

    @Override
    public Long getCompetitionId(Long questionId) {
        return mapper.selectCompetitionId(questionId);
    }

}
