package com.xoj.backend.controller;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.entity.Question;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question")
@Api(value = "question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @PostMapping("/")
    @RequirePermission
    @ApiOperation(value = "create a question")
    public RestResponse<?> createQuestion(@Valid @RequestBody QuestionCreateDto dto) {
        service.create(dto);
        return RestResponse.ok(dto, "success");
    }

    @PutMapping("/")
    @ApiOperation(value = "update a question")
    public RestResponse<?> updateQuestion(@Valid @RequestBody QuestionModifyDto dto) {
        service.modify(dto);
        return RestResponse.ok(dto, "success");
    }

    @GetMapping("/{questionId}")
    @ApiOperation(value = "get one question")
    public RestResponse<Question> getQuestion(@PathVariable("questionId") Long id) {
        Question question = service.selectOneQuestion(id);
        return RestResponse.ok(question, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/question_list")
    @ApiOperation(value = "get questions")
    public RestResponse<PageInfo<Question>> getQuestionList(@RequestBody QuestionPageDto dto) {
        PageInfo<Question> pageInfo = service.selectQuestions(dto);
        return RestResponse.ok(pageInfo, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/all_questions")
    @ApiOperation(value = "get all questions")
    public RestResponse<PageInfo<Question>> getAllQuestions(@RequestBody QuestionPageDto dto) {
        PageInfo<Question> pageInfo = service.selectAllQuestions(dto);
        return RestResponse.ok(pageInfo, CommonErrorType.SUCCESS.getResultMsg());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete a question")
    public RestResponse<?> deleteQuestion(@RequestParam Long id){
        service.delete(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }
}
