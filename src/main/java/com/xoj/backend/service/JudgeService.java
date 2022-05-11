package com.xoj.backend.service;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.param.PlaygroundParam;

import java.util.UUID;

/**
 * @author 1iin
 */
public interface JudgeService {

    UUID submitUpstream(PlaygroundParam param);

    JudgeUpstream lookupUpstream(UUID token);

    SubmitRecordsCreateDto dtoConversion(PlaygroundParam param, JudgeUpstream result);

}
