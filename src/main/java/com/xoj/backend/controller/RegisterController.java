package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.MailRegisterParam;
import com.xoj.backend.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/***
 * @Author jianghanchen
 * @Date 15:48 2022/3/25
 ***/

@RestController
@RequestMapping("/register")
@Api(value = "User register")
public class RegisterController {

    @Autowired
    LoginServiceImpl loginService;

    @RequestMapping(value = "mail", method = RequestMethod.POST)
    @ApiOperation(value = "first register")
    public RestResponse<UserBase> mailRegister(@Valid @RequestBody MailRegisterParam registerParam) {
        return loginService.mailRegister(registerParam);
    }

}
