package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.QuestionCompetitionCreateDto;
import com.xoj.backend.dto.QuestionCompetitionModifyDto;
import com.xoj.backend.entity.QuestionCompetition;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.service.QuestionCompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 1iin
 */
@RestController
@RequestMapping("/question_competition")
@Api(value = "questionCompetition")
public class QuestionCompetitionController {
    @Autowired
    private QuestionCompetitionService service;

    @PostMapping("/")
    @ApiOperation(value = "link a question to a competition")
    @RequireManagerPermission
    public RestResponse<?> create(@Valid @RequestBody QuestionCompetitionCreateDto dto) {
        try {
            service.create(dto);
            return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
        } catch (DuplicateKeyException e) {
            return RestResponse.error(CommonErrorType.DUPLICATE_KEY.getResultMsg());
        }


    }

    @PutMapping("")
    @ApiOperation(value = "modify question score")
    @RequireManagerPermission
    public RestResponse<?> modify(QuestionCompetitionModifyDto dto) {
        service.modify(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @DeleteMapping("")
    @ApiOperation(value = "modify a record of the relationship between a question and a competition")
    @RequireManagerPermission
    public RestResponse<?> delete(@RequestParam Long id) {
        service.delete(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("")
    @ApiOperation(value = "show all the questions of one competition")
    @RequireManagerPermission
    public RestResponse<List<QuestionCompetition>> selectQuestionsByCompetition(@RequestParam Long competitionId) {
        List<QuestionCompetition> list = service.selectQuestionsByCompetition(competitionId);
        return RestResponse.ok(list, CommonErrorType.SUCCESS.getResultMsg());
    }

}
