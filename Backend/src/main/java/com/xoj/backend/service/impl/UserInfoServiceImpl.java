package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.LoginService;
import com.xoj.backend.service.UserInfoService;
import com.xoj.backend.util.UserThreadLocal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;

/***
 * @Author jianghanchen
 * @Date 14:05 2022/3/20
 ***/



@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {


    private final UserBaseMapper mapper;

    @Autowired
    LoginService loginService;



    @Override
    public RestResponse<?> managerDeleteUser(String mail) {
        UserBase user = UserBase.builder().mail(mail).isDelete(true).build();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("mail",mail);
        int delete = mapper.updateByExampleSelective(user, example);
        if(delete == 1){
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public RestResponse<?> deleteUser(String mail) {
        UserBase user = UserBase.builder().mail(mail).isDelete(true).build();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("mail",Session.getCurrentUserMail());
        int delete = mapper.updateByExampleSelective(user, example);
        if(delete == 1){
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public RestResponse<UserBase> managerChangeInfo(UserParam param) {
        UserBase user = UserBase.builder().name(param.getName()).phoneNumber(param.getPhoneNumber()).build();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("name", Session.getCurrentUserName());
        int update = mapper.updateByExampleSelective(user, example);
        if(update == 1){
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public RestResponse<UserBase> changeInfo(UserParam param) {
        UserBase user = UserBase.builder()
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .mail(param.getMail())
                .password(param.getPassword())
                .ranking(param.getRanking())
                .score(param.getScore())
                .authority(param.getAuthority())
                .build();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", param.getId());
        int update = mapper.updateByExampleSelective(user, example);
        if(update == 1){
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public RestResponse<UserBase> addUser(UserParam param) {
        UserBase user = UserBase.builder()
                .id(param.getId())
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .mail(param.getMail())
                .password(param.getPassword())
                .ranking(param.getRanking())
                .score(param.getScore())
                .authority(param.getAuthority())
                .build();

        int insert = mapper.insertSelective(user);
        if(insert == 1){
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }






}
