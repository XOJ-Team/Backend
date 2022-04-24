package com.xoj.backend.service;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.model.SubmitRecordsModel;

import java.util.List;

public interface SubmitRecordsService {
    void createRecord(SubmitRecordsCreateDto dto);

    List<SubmitRecordsModel> selectQuestionRecords(Long questionId);

    List<SubmitRecordsModel> selectUserRecords(Long userId);

    void modifyRecord(SubmitRecordsModifyDto dto);

    List<Long> solved(Long userId);

    SubmitRecordsModel selectRecord(Long id);
}
