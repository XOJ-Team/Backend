package com.xoj.backend.service;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.entity.SubmitRecords;

import java.util.List;

public interface SubmitRecordsService {
    void createRecord(SubmitRecordsCreateDto dto);

    List<SubmitRecords> selectQuestionRecords(Long questionId);

    List<SubmitRecords> selectUserRecords();
}
