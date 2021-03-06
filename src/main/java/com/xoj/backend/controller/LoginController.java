package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.MailLoginParam;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.service.LoginService;
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
 * @Date 16:32 2022/3/12
 ***/

@RestController
@RequestMapping("/login")
@Api(value = "User login")
public class LoginController {

    @Autowired
    LoginService loginService;

    private static final String NORMAL_LOGIN_URL = "/normal";
    private static final String MAIL_LOGIN_URL = "/mail";

    @RequestMapping(value = NORMAL_LOGIN_URL, method = RequestMethod.POST)
    @ApiOperation(value = "login without use verification number")
    public RestResponse<UserBase> normalLogin(@Valid @RequestBody NormalLoginParam loginParam) {
        Session.getSession().setMaxInactiveInterval(5184000);
        return loginService.normalLogin(loginParam);
    }

    @RequestMapping(value = MAIL_LOGIN_URL, method = RequestMethod.POST)
    @ApiOperation(value = "mail login")
    public RestResponse<UserBase> mailLogin(@Valid @RequestBody MailLoginParam loginParam) {
        Session.getSession().setMaxInactiveInterval(5184000);
        return loginService.mailLogin(loginParam);
    }






}
