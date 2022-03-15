package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.entity.User;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.service.LogoutService;
import com.xoj.backend.service.impl.LogoutServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/***
 * @Author yezhen
 * @Date 10:53 2022/3/15
 ***/



@RestController

@Api(value = "User logout")
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ApiOperation(value = "logout")
    public RestResponse<Object> logout() {
        return logoutService.logout();
    }

}
