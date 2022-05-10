package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.param.PlaygroundParam;

import java.util.UUID;

/**
 * @author 1iin
 */
public interface JudgeService {

//    RestResponse<Judge> runOnly(PlaygroundParam param);
//
//    RestResponse<Judge> runAndSubmit(PlaygroundParam param);

    UUID submitUpstream(PlaygroundParam param);

    RestResponse<JudgeUpstream> lookupUpstream(UUID token);

}
