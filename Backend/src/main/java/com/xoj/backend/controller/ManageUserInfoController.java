package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.UserInfoService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/***
 * @Author jianghanchen
 * @Date 18:52 2022/3/20
 ***/
@RestController
@Api(value = "Manager use this controller to modify user information")
public class ManageUserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }

    @RequestMapping(value = "/manager/modify", method = RequestMethod.POST)
    @ApiOperation(value = "manager modify user information")
    public RestResponse<UserBase> changeInfo(UserParam param) {
        return userInfoService.managerChangeInfo(param);
    }


    @RequestMapping(value = "/manager/delete", method = RequestMethod.POST)
    @ApiOperation(value = "delete account")
    public RestResponse<?> delete(String mail) {
        return userInfoService.managerDeleteUser(mail);
    }

    @RequestMapping(value = "/manager/insert", method = RequestMethod.POST)
    @ApiOperation(value = "insert user")
    public RestResponse<?> insert(UserParam param) {
        return userInfoService.addUser(param);
    }

    @GetMapping("/manager/user")
    @ApiOperation(value = "get user info")
    public RestResponse<UserBase> getUser(){
        try {
            UserBase userBase = userInfoService.selectUser();
            return RestResponse.ok(userBase, CommonErrorType.SUCCESS.getResultMsg());
        } catch (Exception e) {
            return RestResponse.error(CommonErrorType.NO_USER.getResultMsg());
        }

    }

}