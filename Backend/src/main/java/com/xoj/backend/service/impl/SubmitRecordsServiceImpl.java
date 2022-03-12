package com.xoj.backend.service.impl;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.entity.User;
import com.xoj.backend.mapper.SubmitRecordsMapper;
import com.xoj.backend.service.SubmitRecordsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@AllArgsConstructor
public class SubmitRecordsServiceImpl implements SubmitRecordsService {

    private final SubmitRecordsMapper mapper;

    /**
     * create submit records
     * @param dto
     */
    @Override
    public void createRecord(SubmitRecordsCreateDto dto) {
        // TODO get user
        // TODO judge
        SubmitRecords record = SubmitRecords.builder()
                .questionId(dto.getQuestionId())
                .lang(dto.getLang())
                .memoryCost(dto.getMemoryCost())
                .timeCost(dto.getTimeCost()).build();
        mapper.insert(record);
    }

    /**
     * return submit records of a user in this question
     * @param questionId
     * @return
     */
    @Override
    public List<SubmitRecords> selectQuestionRecords(Long questionId) {
        // TODO get user
        User user = new User();
        Example example = new Example(SubmitRecords.class);
        example.createCriteria()
                .andEqualTo("questionId", questionId)
                .andEqualTo("userId", user.getId());
        return mapper.selectByExample(example);
    }

    /**
     * return submit records of a user
     * @return
     */
    @Override
    public List<SubmitRecords> selectUserRecords() {
        // TODO get user
        User user = new User();
        Example example = new Example(SubmitRecords.class);
        example.createCriteria()
                .andEqualTo("userId", user.getId());
        return mapper.selectByExample(example).subList(0, 10);
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
}
