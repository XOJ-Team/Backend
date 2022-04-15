package com.xoj.backend.controller;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.QuestionCreateDto;
import com.xoj.backend.dto.QuestionModifyDto;
import com.xoj.backend.dto.QuestionPageDto;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.model.QuestionPageModel;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.SubmitRecordsService;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
@Api(value = "question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private SubmitRecordsService submitRecordsService;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }


    @PostMapping("/")
    @RequireManagerPermission
    @ApiOperation(value = "create a question")
    public RestResponse<?> createQuestion(@Valid @RequestBody QuestionCreateDto dto) {
        service.create(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/")
    @ApiOperation(value = "update a question")
    @RequireManagerPermission
    public RestResponse<?> updateQuestion(@Valid @RequestBody QuestionModifyDto dto) {
        service.modify(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/{questionId}")
    @ApiOperation(value = "get one question")
    public RestResponse<QuestionModel> getQuestion(@PathVariable("questionId") Long id) {
        try {
            QuestionModel question = service.selectOneQuestion(id);
            return RestResponse.ok(question, CommonErrorType.SUCCESS.getResultMsg());
        }catch (BizException e) {
            return RestResponse.error(null, CommonErrorType.QUESTION_NOT_FOUND.getResultMsg());
        }
    }

    @GetMapping("/question_list")
    @ApiOperation(value = "get questions")
    public RestResponse<PageInfo<QuestionModel>> getQuestionList(@RequestBody QuestionPageDto dto) {
        PageInfo<QuestionModel> pageInfo = service.selectQuestions(dto);
        return RestResponse.ok(pageInfo, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/all_questions")
    @ApiOperation(value = "get all questions")
    public RestResponse<QuestionPageModel> getAllQuestions(@RequestParam Integer pageNum,
                                                            @RequestParam Integer pageSize) {
        QuestionPageModel questionPageModel = QuestionPageModel.builder().build();
        UserBase user = userBaseService.getCurrentUser();
        if (null != user) {
            List<Long> questionIds = submitRecordsService.solved(user.getId());
            if (null == questionIds) {
                questionPageModel.setQuestionIds(new ArrayList<>());
            } else {
                questionPageModel.setQuestionIds(questionIds);
            }
        } else {
            questionPageModel.setQuestionIds(new ArrayList<>());
        }
        QuestionPageDto dto = QuestionPageDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
        PageInfo<QuestionModel> pageInfo = service.selectAllQuestions(dto);
        questionPageModel.setQuestionsPage(pageInfo);
        return RestResponse.ok(questionPageModel, CommonErrorType.SUCCESS.getResultMsg());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete a question")
    @RequireManagerPermission
    public RestResponse<?> deleteQuestion(@RequestParam Long id){
        service.delete(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("all_show_questions")
    @ApiOperation(value = "get all show questions")
    public RestResponse<QuestionPageModel> getAllShowQuestions(@RequestParam Integer pageNum,
                                                               @RequestParam Integer pageSize) {
        QuestionPageModel questionPageModel = QuestionPageModel.builder().build();
        UserBase user = userBaseService.getCurrentUser();
        if (null != user) {
            List<Long> questionIds = submitRecordsService.solved(user.getId());
            if (null == questionIds) {
                questionPageModel.setQuestionIds(new ArrayList<>());
            } else {
                questionPageModel.setQuestionIds(questionIds);
            }
        } else {
            questionPageModel.setQuestionIds(new ArrayList<>());
        }
        QuestionPageDto dto = QuestionPageDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
        PageInfo<QuestionModel> pageInfo = service.selectAllShowQuestions(dto);
        questionPageModel.setQuestionsPage(pageInfo);
        return RestResponse.ok(questionPageModel, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/hide")
    @ApiOperation(value = "hide a question")
    @RequireManagerPermission
    public RestResponse<?> hideQuestion(@RequestParam Long id) {
        service.hide(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/show")
    @ApiOperation(value = "show a question")
    @RequireManagerPermission
    public RestResponse<?> showQuestion(@RequestParam Long id) {
        service.show(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/search")
    @ApiOperation(value = "search questions when linking")
    @RequireManagerPermission
    public RestResponse<?> showQuestion(@RequestParam String text) {
        return RestResponse.ok(service.search(text), CommonErrorType.SUCCESS.getResultMsg());
    }
}
