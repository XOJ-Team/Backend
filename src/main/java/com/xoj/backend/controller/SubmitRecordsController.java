package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.dto.SubmitRecordsModifyDto;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.model.SubmitRecordsModel;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.service.SubmitRecordsService;
import com.xoj.backend.util.UserThreadLocal;
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

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }

    @PostMapping("/")
    @RequirePermission
    @ApiOperation(value = "create submit record")
    public RestResponse<?> create(@RequestBody SubmitRecordsCreateDto dto) {
        try {
            submitRecordsService.createRecord(dto);
            return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
        } catch (BizException e) {
            return RestResponse.error(null, e.getErrorMsg());
        }

    }

    @GetMapping("/question_records")
    @RequirePermission
    @ApiOperation(value = "show the records of one question")
    public RestResponse<List<SubmitRecordsModel>> selectByQuestion(@RequestParam Long questionId) {
        List<SubmitRecordsModel> submitRecords = submitRecordsService.selectQuestionRecords(questionId);
        return RestResponse.ok(submitRecords, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/user_records")
    @ApiOperation(value = "show the records of a user")
    public RestResponse<List<SubmitRecordsModel>> selectByUser(@RequestParam Long userId) {
        List<SubmitRecordsModel> submitRecords = submitRecordsService.selectUserRecords(userId);
        return RestResponse.ok(submitRecords, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/")
    @RequirePermission
    @ApiOperation(value = "modify the comment of a record")
    public RestResponse<?> modify(@RequestBody SubmitRecordsModifyDto dto) {
        submitRecordsService.modifyRecord(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/{recordId}")
    @RequirePermission
    public RestResponse<SubmitRecordsModel> oneRecord(@PathVariable("recordId") Long id) {
        try {
            SubmitRecordsModel record = submitRecordsService.selectRecord(id);
            return RestResponse.ok(record, CommonErrorType.SUCCESS.getResultMsg());
        } catch (BizException e) {
            return RestResponse.error(null, CommonErrorType.SUBMIT_RECORD_NOT_FOUND.getResultMsg());
        }
    }

}
