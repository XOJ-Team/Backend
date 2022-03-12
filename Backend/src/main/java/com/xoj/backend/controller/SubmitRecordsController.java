package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.service.SubmitRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "submit records")
@RequestMapping("/submit_records")
public class SubmitRecordsController {

    @Autowired
    private SubmitRecordsService submitRecordsService;

    @GetMapping("/question_records")
    @ApiOperation(value = "show the records of one question")
    public RestResponse<List<SubmitRecords>> selectByQuestion(@RequestParam Long questionId) {
        List<SubmitRecords> submitRecords = submitRecordsService.selectQuestionRecords(questionId);
        return RestResponse.ok(submitRecords, "success");
    }

    @GetMapping("/user_records")
    @ApiOperation(value = "show the records of a user")
    public RestResponse<List<SubmitRecords>> selectByUser() {
        List<SubmitRecords> submitRecords = submitRecordsService.selectUserRecords();
        return RestResponse.ok(submitRecords, "success");
    }
}
