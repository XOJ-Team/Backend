package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Author jianghanchen
 * @Date 15:17 2022/3/20
 ***/

@RestController
@Api(value = "Used to modify user information")
public class InfoManagementController {

    @Autowired
    UserInfoService userInfoService;


    @RequestMapping(value = "/user/modify", method = RequestMethod.POST)
    @ApiOperation(value = "user modify itself")
    public RestResponse<UserBase> changeInfo(UserParam param) {
        return userInfoService.changeInfo(param);
    }


    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete account")
    public RestResponse<?> delete(String mail) {
        return userInfoService.deleteUser(mail);
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.POST)
    @ApiOperation(value = "get user' information by id")
    public RestResponse<?> getUserInfo(String id) {
        return userInfoService.getUserInfo(id);
    }


}

