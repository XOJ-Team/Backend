package com.xoj.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.common.CommonConstants;
import com.xoj.backend.common.LevelEnum;
import com.xoj.backend.dto.UserRankingPageDto;
import com.xoj.backend.exception.BizException;
import com.xoj.backend.exception.CommonErrorType;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.model.UserRankingModel;
import com.xoj.backend.param.ChangeUserInfoParam;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.service.*;
import com.xoj.backend.util.TransUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/***
 * @Author jianghanchen
 * @Date 14:05 2022/3/20
 ***/



@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {


    private final UserBaseMapper mapper;

    private final UserBaseService userBaseService;

    private final UploadImageService uploadImageService;

    @Autowired
    LoginService loginService;


    private final QuestionService questionService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        UserBase user = UserBase.builder()
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .authority(param.getAuthority())
                .score(param.getScore())
                .mail(param.getMail())
                .ranking(param.getRanking())
                .build();
        if (null != param.getPassword()) {
            user.setPassword(TransUtils.getMd5(param.getPassword()));
        }
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", param.getId());
        int update = mapper.updateByExampleSelective(user, example);
        if(update == 1){
            return RestResponse.ok();
        }else{
            return RestResponse.error();
        }
    }

    @Override
    public RestResponse<UserBase> getUserInfo(String id) {
        UserBase user = loginService.getUserById(id);
        if(user != null){
            if (!StringUtils.hasText(user.getProfilePhoto())) {
                user.setProfilePhoto(CommonConstants.DEFAULT_PHOTO);
            }
            return RestResponse.ok(user);
        }
        return RestResponse.error("no such user");
    }

    @Override
    public RestResponse<UserBase> changeInfo(ChangeUserInfoParam param) {
        UserBase user = UserBase.builder()
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .mail(param.getMail())
                .build();
        if (StringUtils.hasText(param.getPassword())) {
            user.setPassword(TransUtils.getMd5(param.getPassword()));
        }
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("mail", param.getMail());
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
        if (null == user) {
            throw new BizException(CommonErrorType.NO_USER);
        }
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", user.getId());
        UserBase userBase = mapper.selectOneByExample(example);
        return userBase;
    }

    @Override
    public String updateImage(MultipartFile smfile) {
        logger.info("start update");
        UserBase user = userBaseService.getCurrentUser();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", user.getId());
        String url = uploadImageService.uploadPicture(smfile);
        UserBase userBase = UserBase.builder()
                .profilePhoto(url)
                .build();
        logger.info("insert url");
        mapper.updateByExampleSelective(userBase, example);
        return url;
    }

    @Override
    public String updateIntro(String text) {
        UserBase user = userBaseService.getCurrentUser();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("id", user.getId());
        UserBase userBase = UserBase.builder()
                .intro(text)
                .build();
        mapper.updateByExampleSelective(userBase, example);
        return text;
    }

    @Override
    public PageInfo<UserRankingModel> selectUsers(UserRankingPageDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<UserRankingModel> users = mapper.selectUsersOrderedByRanking();
        return PageInfo.of(users);
    }


}
