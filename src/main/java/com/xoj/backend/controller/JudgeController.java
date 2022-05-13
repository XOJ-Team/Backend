package com.xoj.backend.controller;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.param.JudgeParam;
import com.xoj.backend.entity.TestcaseQuestion;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.param.PlaygroundRequestParam;
import com.xoj.backend.param.PlaygroundResponseParam;
import com.xoj.backend.service.JudgeService;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.SubmitRecordsService;
import com.xoj.backend.service.TestcaseQuestionService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/judge")
@Api(value = "Judging Service")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private SubmitRecordsService submitRecordsService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestcaseQuestionService testcaseQuestionService;

    private static final String RUN_ONLY_URL = "/run";
    private static final String RUN_AND_SUBMIT_URL = "/submit";

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }

    @RequestMapping(value = RUN_ONLY_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Run code and check with user-provided input")
    @RequirePermission
    public RestResponse<PlaygroundResponseParam> run(@Valid @RequestBody PlaygroundRequestParam playgroundRequest) {
        JudgeParam judgeRequest = new JudgeParam(playgroundRequest);
        UUID token = judgeService.submitUpstream(judgeRequest);
        return RestResponse.ok(new PlaygroundResponseParam(judgeService.lookupUpstream(token), playgroundRequest));
    }

    @RequestMapping(value = RUN_AND_SUBMIT_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Submit code and check with stored test cases")
    @RequirePermission
    public RestResponse<PlaygroundResponseParam> submit(@Valid @RequestBody PlaygroundRequestParam playgroundRequest) {
        QuestionModel question = questionService.selectOneQuestion(playgroundRequest.getQuestion_id());
        List<TestcaseQuestion> testcaseList = testcaseQuestionService.testcases(playgroundRequest.getQuestion_id());
        JudgeParam judgeRequest = new JudgeParam(playgroundRequest);
        JudgeParam judgeResponse = new JudgeParam();
        judgeRequest.setMemory((double) question.getMemoryLimit() * 1000);
        judgeRequest.setTime((double) question.getTimeLimit());
        for (TestcaseQuestion t : testcaseList) {
            judgeRequest.setStdin(Base64.getEncoder().encodeToString(t.getTestcase().getBytes()));
            judgeRequest.setExpected_output(Base64.getEncoder().encodeToString(t.getResult().getBytes()));
            UUID token = judgeService.submitUpstream(judgeRequest);
            judgeResponse = judgeService.lookupUpstream(token);

            if (judgeResponse.getStatus().getId() != 3) {
                break;
            }
        }
        try {
            SubmitRecordsCreateDto dto = judgeService.dtoConversion(playgroundRequest, judgeResponse);
            submitRecordsService.createRecord(dto);
//            return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
        } catch (BizException e) {
            return RestResponse.error(null, e.getErrorMsg());
        }
        return RestResponse.ok(new PlaygroundResponseParam(judgeResponse, playgroundRequest));
    }

}
