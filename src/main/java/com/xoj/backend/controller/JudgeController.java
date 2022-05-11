package com.xoj.backend.controller;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.entity.TestcaseQuestion;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.param.PlaygroundParam;
import com.xoj.backend.service.JudgeService;
import com.xoj.backend.service.SubmitRecordsService;
import com.xoj.backend.service.TestcaseQuestionService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public RestResponse<JudgeUpstream> runCode(@Valid @RequestBody PlaygroundParam param) {
        UUID token = judgeService.submitUpstream(param);
        return RestResponse.ok(judgeService.lookupUpstream(token));
    }

    @RequestMapping(value = RUN_AND_SUBMIT_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Submit code and check with stored test cases")
    @RequirePermission
    public RestResponse<JudgeUpstream> submitCode(@Valid @RequestBody PlaygroundParam param) {
        List<TestcaseQuestion> testcaseList = testcaseQuestionService.testcases(param.getQuestion_id());
        JudgeUpstream result = new JudgeUpstream();
        for (TestcaseQuestion t : testcaseList) {
            param.setStdin(t.getTestcase());
            param.setExpected_output(t.getResult());
            UUID token = judgeService.submitUpstream(param);
            result = judgeService.lookupUpstream(token);

            if (result.getStatus().getId() == 4) {
                break;
            }
        }
        SubmitRecordsCreateDto dto = judgeService.dtoConversion(param, result);
        try {
            submitRecordsService.createRecord(dto);
//            return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
        } catch (BizException e) {
            return RestResponse.error(null, e.getErrorMsg());
        }

        return RestResponse.ok(result);
    }

}
