package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.CompetitionCreateDto;
import com.xoj.backend.dto.CompetitionModifyDto;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.model.CompetitionModel;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RestResponse<?> createCompetition(@Valid @RequestBody CompetitionCreateDto dto) {
        service.create(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @PutMapping("/")
    @ApiOperation(value = "update a competition")
    @RequireManagerPermission
    public RestResponse<?> updateCompetition(@Valid @RequestBody CompetitionModifyDto dto) {
        service.modify(dto);
        return RestResponse.ok(dto, CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/{competitionId}")
    @ApiOperation(value = "get one competition")
    public RestResponse<CompetitionModel> getCompetition(@PathVariable("competitionId") Long id) {
        CompetitionModel competition = service.selectOneCompetition(id);
        return RestResponse.ok(competition, CommonErrorType.SUCCESS.getResultMsg());
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete a competition")
    @RequireManagerPermission
    public RestResponse<?> deleteCompetition(@RequestParam Long id) {
        service.delete(id);
        return RestResponse.ok(id, CommonErrorType.SUCCESS.getResultMsg());
    }
}
