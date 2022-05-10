package com.xoj.backend.controller;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.param.PlaygroundParam;
import com.xoj.backend.service.JudgeService;
import com.xoj.backend.service.impl.JudgeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/judge")
@Api(value = "Judging Service")
public class JudgeController {

    JudgeService judgeService = new JudgeServiceImpl();
    private static final String RUN_ONLY_URL = "/run";
    private static final String RUN_AND_SUBMIT_URL = "/submit";

    @RequestMapping(value = RUN_ONLY_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Run code and check with user-provided input")
    public RestResponse<JudgeUpstream> runCode(@Valid @RequestBody PlaygroundParam param) {
        UUID token = judgeService.submitUpstream(param);
        return judgeService.lookupUpstream(token);
    }

    @RequestMapping(value = RUN_AND_SUBMIT_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Submit code and check with stored test cases")
    public void submitCode(@Valid @RequestBody PlaygroundParam param) {
        System.out.println("Submit code request received");
    }
}
