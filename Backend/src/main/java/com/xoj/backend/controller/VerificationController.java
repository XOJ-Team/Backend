package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.param.VerificationParam;
import com.xoj.backend.service.VerificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/***
 * @Author yezhen
 * @Date 17:17 2022/3/13
 ***/

@RestController
@Api(value = "send verification code")
public class VerificationController{

    @Autowired
    VerificationService verificationService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public RestResponse<Object> send(@Valid @RequestBody VerificationParam param) {
        return verificationService.sendVerificationNumber(param.getMail());
    }

    @RequestMapping(value = "/send/password", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public RestResponse<Object> sendPassword(@Valid @RequestBody VerificationParam param) {
        return verificationService.sendVerificationNumber(param.getMail());
    }
}
