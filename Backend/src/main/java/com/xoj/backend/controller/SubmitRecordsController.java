package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.service.SubmitRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "submit records")
@RequestMapping("/submit_records")
public class SubmitRecordsController {

    @Autowired
    private SubmitRecordsService submitRecordsService;

    @GetMapping("/question_records")
    @RequirePermission
    @ApiOperation(value = "show the records of one question")
    public RestResponse<List<SubmitRecords>> selectByQuestion(@RequestParam Long questionId) {
        List<SubmitRecords> submitRecords = submitRecordsService.selectQuestionRecords(questionId);
        return RestResponse.ok(submitRecords, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/user_records")
    @RequirePermission
    @ApiOperation(value = "show the records of a user")
    public RestResponse<List<SubmitRecords>> selectByUser() {
        List<SubmitRecords> submitRecords = submitRecordsService.selectUserRecords();
        return RestResponse.ok(submitRecords, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PostMapping("/modify")
    @ApiOperation(value = "modify the comment of a record")
    public RestResponse<?> modify(@RequestBody SubmitRecordsModifyDto dto) {
        submitRecordsService.modifyRecord(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }
}
