package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 1iin
 */
@RestController
@RequestMapping("/competition")
@Api(value = "Competition")
public class CompetitionController {
    @Autowired
    private CompetitionService service;

    @PostMapping("/")
    @RequireManagerPermission
    @ApiOperation(value = "create a competition")
    public RestResponse<?> createQuestion(@Valid @RequestBody CompetitionCreateDto dto) {
        service.create(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }
}
