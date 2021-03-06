package com.xoj.backend.controller;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.UserModifyDto;
import com.xoj.backend.dto.UserRankingPageDto;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.model.UserRankingModel;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.param.ChangeUserInfoParam;
import com.xoj.backend.service.UserInfoService;
import com.xoj.backend.util.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/***
 * @Author jianghanchen
 * @Date 15:17 2022/3/20
 ***/

@RestController
@Api(value = "Used to modify user information")
public class InfoManagementController {

    @Autowired
    UserInfoService userInfoService;

    @ModelAttribute
    public void setUser() {
        UserThreadLocal.set();
    }


    @RequestMapping(value = "/user/modify", method = RequestMethod.POST)
    @ApiOperation(value = "user modify itself")
    public RestResponse<UserBase> changeInfo(@Valid @RequestBody ChangeUserInfoParam param) {
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

    @PostMapping("/user/image")
    @RequirePermission
    @ApiOperation(value = "update profile image")
    public RestResponse<?> changeImage(MultipartFile smfile) {
        return RestResponse.ok(userInfoService.updateImage(smfile), CommonErrorType.SUCCESS.getResultMsg());
    }

    @PostMapping("/user/intro")
    @RequirePermission
    @ApiOperation(value = "update intro")
    public RestResponse<String> updateIntro(@RequestBody UserModifyDto dto) {
        return RestResponse.ok(userInfoService.updateIntro(dto.getText()), CommonErrorType.SUCCESS.getResultMsg());
    }

    @GetMapping("/user/my")
    @ApiOperation(value = "get user info")
    public RestResponse<UserBase> getUser() {
        try {
            UserBase userBase = userInfoService.selectUser();
            return RestResponse.ok(userBase, CommonErrorType.SUCCESS.getResultMsg());
        } catch (Exception e) {
            return RestResponse.error(CommonErrorType.NO_USER.getResultMsg());
        }
    }

    @GetMapping("/user/rankinglist")
    @ApiOperation(value = "get user ranking list")
    public RestResponse<PageInfo<UserRankingModel>> getUserRankingList(@RequestParam Integer pageNum,
                                                                       @RequestParam Integer pageSize) {
        UserRankingPageDto dto = UserRankingPageDto.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
        return RestResponse.ok(userInfoService.selectUsers(dto), CommonErrorType.SUCCESS.getResultMsg());
    }
}

