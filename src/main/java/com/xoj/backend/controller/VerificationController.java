package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.param.VerificationParam;
import com.xoj.backend.service.MailGunService;
import com.xoj.backend.service.VerificationService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/***
 * @Author jianghanchen
 * @Date 17:17 2022/3/13
 ***/

@RestController
@Api(value = "send verification code")
public class VerificationController{

    @Autowired
    VerificationService verificationService;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }


    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public RestResponse<Object> send(@Valid @RequestBody VerificationParam param) {
        return verificationService.sendVerificationNumber(param.getFrom(), param.getMail());
    }

    @RequestMapping(value = "/send/password", method = RequestMethod.POST)
    @ApiOperation(value = "send verification code")
    public RestResponse<Object> sendPassword(@Valid @RequestBody VerificationParam param) {
        return verificationService.sendVerificationNumber(param.getMail());
    }
}
