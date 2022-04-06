package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.common.LevelEnum;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.LoginService;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.service.UserBaseService;
import com.xoj.backend.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/***
 * @Author jianghanchen
 * @Date 14:05 2022/3/20
 ***/



@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {


    private final UserBaseMapper mapper;

    private final UserBaseService userBaseService;

    @Autowired
    LoginService loginService;


    private final QuestionService questionService;


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
    public RestResponse<UserBase> getUserInfo(String id) {
        UserBase user = loginService.getUserById(id);
        if(user != null) return RestResponse.ok(user);
        return RestResponse.error("no such user");
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


    @Override
    public void updateSolvedQuestions(Long questionId, UserBase user) {
        QuestionModel questionModel = questionService.selectOneQuestion(questionId);
        Integer level = questionModel.getQuestionLevel();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", user.getId());
        // query the number of solved question of the user
        user = mapper.selectOneByExample(example);
        UserBase userBase = new UserBase();
        if (LevelEnum.EASY.getCode().equals(level)) {
            userBase.setEasyNumber(user.getEasyNumber() + 1);
        }else if (LevelEnum.MEDIUM.getCode().equals(level)) {
            userBase.setMediumNumber(user.getMediumNumber() + 1);
        }else {
            userBase.setHardNumber(user.getHardNumber() + 1);
        }
        userBase.setSolvedNumber(user.getSolvedNumber() + 1);
        mapper.updateByExampleSelective(userBase, example);
    }

    @Override
    public UserBase selectUser() {
        UserBase user = userBaseService.getCurrentUser();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", user.getId());
        UserBase userBase = mapper.selectOneByExample(example);
        return userBase;
    }


}
