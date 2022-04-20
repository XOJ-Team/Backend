package com.xoj.backend.service;

import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.dto.UserRankingPageDto;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.model.UserRankingModel;
import com.xoj.backend.param.ChangeUserInfoParam;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.param.UserParam;
import org.springframework.web.multipart.MultipartFile;

/***
 * @Author jianghanchen
 * @Date 13:13 2022/3/20
 ***/


public interface UserInfoService {

    RestResponse<UserBase> changeInfo(ChangeUserInfoParam param);

    RestResponse<UserBase> getUserInfo(String id);

    RestResponse<UserBase> managerChangeInfo(UserParam param);

    RestResponse<UserBase> addUser(UserParam param);

    RestResponse<?> deleteUser(String mail);

    RestResponse<?> managerDeleteUser(String mail);

    void updateSolvedQuestions(Long questionId, UserBase user);

    UserBase selectUser();

    String updateImage(MultipartFile smfile);

    String updateIntro(String text);

    PageInfo<UserRankingModel> selectUsers(UserRankingPageDto dto);
}
