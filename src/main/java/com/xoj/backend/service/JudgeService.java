package com.xoj.backend.service;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.param.JudgeParam;
import com.xoj.backend.param.PlaygroundRequestParam;

import java.util.UUID;

/**
 * @author 1iin
 */
public interface JudgeService {

    UUID submitUpstream(JudgeParam param);

    JudgeParam lookupUpstream(UUID token);

    SubmitRecordsCreateDto dtoConversion(PlaygroundRequestParam param, JudgeParam result);

}
