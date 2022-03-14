package com.xoj.backend.service.impl;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.entity.User;
import com.xoj.backend.mapper.SubmitRecordsMapper;
import com.xoj.backend.service.SubmitRecordsService;
import com.xoj.backend.service.UserBaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@AllArgsConstructor
public class SubmitRecordsServiceImpl implements SubmitRecordsService {

    private final SubmitRecordsMapper mapper;

    private final UserBaseService userBaseService;

    /**
     * create submit records
     * @param dto
     */
    @Override
    public void createRecord(SubmitRecordsCreateDto dto) {
        User user = userBaseService.getCurrentUser();
        // TODO judge
        SubmitRecords record = SubmitRecords.builder()
                .questionId(dto.getQuestionId())
                .lang(dto.getLang())
                .memoryCost(dto.getMemoryCost())
                .timeCost(dto.getTimeCost())
                .codes(dto.getCodes()).build();
        mapper.insertSelective(record);
    }

    /**
     * return submit records of a user in this question
     * @param questionId
     * @return
     */
    @Override
    public List<SubmitRecords> selectQuestionRecords(Long questionId) {
        User user = userBaseService.getCurrentUser();
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
        User user = userBaseService.getCurrentUser();
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
