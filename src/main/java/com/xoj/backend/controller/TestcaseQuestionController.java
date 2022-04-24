package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.TestcaseQuestionModifyDto;
import com.xoj.backend.dto.TestcaseQuestionCreateDto;
import com.xoj.backend.entity.TestcaseQuestion;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.service.TestcaseQuestionService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/testcase")
@Api(value = "testcase")
public class TestcaseQuestionController {
    @Autowired
    private TestcaseQuestionService service;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }

    @PostMapping("/")
    @ApiOperation(value = "create a testcase")
    @RequireManagerPermission
    public RestResponse<?> create(@Valid @RequestBody TestcaseQuestionCreateDto dto) {
        service.create(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/")
    @ApiOperation(value = "modify a testcase")
    @RequireManagerPermission
    public RestResponse<?> modify(@Valid @RequestBody TestcaseQuestionModifyDto dto) {
        service.modify(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete a testcase")
    @RequireManagerPermission
    public RestResponse<?> delete(@RequestParam Long id) {
        service.delete(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/")
    @ApiOperation(value = "get testcases")
    @RequireManagerPermission
    public RestResponse<List<TestcaseQuestion>> testcases(@RequestParam Long questionId) {
        List<TestcaseQuestion> list = service.testcases(questionId);
        return RestResponse.ok(list, CommonErrorType.SUCCESS.getResultMsg());
    }

}
