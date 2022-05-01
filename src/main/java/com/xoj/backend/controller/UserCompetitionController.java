package com.xoj.backend.controller;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.UserCompetitionCreateDto;
import com.xoj.backend.dto.UserCompetitionPageDto;
import com.xoj.backend.entity.UserCompetition;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.model.UserCompetitionModel;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.service.UserCompetitionService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 1iin
 */
@RestController
@RequestMapping("/user_competition")
@Api(value = "userCompetition")
public class UserCompetitionController {
    @Autowired
    private UserCompetitionService service;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }

    @PostMapping("/")
    @ApiOperation(value = "user register a competition")
    @RequirePermission
    public RestResponse<?> create(@Valid @RequestBody UserCompetitionCreateDto dto) {
        try {
            service.create(dto);
            return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
        } catch (DuplicateKeyException e) {
            return RestResponse.error(CommonErrorType.DUPLICATE_KEY.getResultMsg());
        }
    }

    @GetMapping("/user_list")
    @ApiOperation(value = "get user ranking list in a competition")
    public RestResponse<PageInfo<UserCompetition>> getQuestionList(@RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize) {
        UserCompetitionPageDto dto = UserCompetitionPageDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
        PageInfo<UserCompetition> pageInfo = service.selectUserCompetition(dto);
        return RestResponse.ok(pageInfo, CommonErrorType.SUCCESS.getResultMsg());
    }


    @DeleteMapping("/")
    @ApiOperation(value = "user quit a competition")
    @RequirePermission
    public RestResponse<?> delete(@RequestParam Long competitionId) {
        service.deleteUserCompetition(competitionId);
        return RestResponse.ok(CommonErrorType.SUCCESS.getResultMsg());
    }

}
