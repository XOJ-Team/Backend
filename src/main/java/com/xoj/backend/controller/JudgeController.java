package com.xoj.backend.controller;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.param.JudgeDownstreamParam;
import com.xoj.backend.service.JudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/judge")
@Api(value = "Judging Service")
public class JudgeController {

    JudgeService judgeService;
    private static final String RUN_ONLY_URL = "/run";
    private static final String RUN_AND_SUBMIT_URL = "/submit";

    @RequestMapping(value = RUN_ONLY_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Run code and check with user-provided input")
    public void runCode(@Valid @RequestBody JudgeDownstreamParam param) {
        System.out.println(param.getLangId() + param.getCode());
    }

    @RequestMapping(value = RUN_AND_SUBMIT_URL, method = RequestMethod.POST)
    @ApiOperation(value = "Submit code and check with stored test cases")
    public void submitCode(@Valid @RequestBody JudgeDownstreamParam param) {
        System.out.println("Submit code request received");
    }
}
