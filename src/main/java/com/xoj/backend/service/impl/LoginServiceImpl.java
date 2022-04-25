package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.notation.RequireManagerPermission;
import com.xoj.backend.notation.RequireProPermission;
import com.xoj.backend.param.*;
import com.xoj.backend.util.UserThreadLocal;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.mapper.UserBaseMapper;
import com.xoj.backend.service.LoginService;
import com.xoj.backend.util.TransUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/***
 * @Author jianghanchen
 * @Date 22:51 2022/3/12
 ***/

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserBaseMapper mapper;

    @Override
    public RestResponse<UserBase> normalLogin(NormalLoginParam param) {
        String mail = param.getMail();
        mail = TransUtils.toLowerCase(mail);
        String password = TransUtils.getMd5(param.getPassword());
        UserBase user = getUser(mail);
        if(user == null){
            return RestResponse.error();
        }
        if(user.getPassword().equals(password)){
            Session.setUserInfo(user);
            Session.setUser(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else {
            return RestResponse.error();
        }

    }

    @Override
    @SuppressWarnings("all")
    public boolean checkVerificationNumber(String verificationNumber) {
        String verificationNumberInSession = (String)Session.getSession().getAttribute("verificationNumber");

        if(verificationNumberInSession != null && verificationNumber.equals(verificationNumberInSession)){
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> mailLogin(MailLoginParam param) {
        if(checkVerificationNumber(param.getVerificationNumber())){
            return RestResponse.error("Verification code error");
        }

        try{
            UserBase user  = getUser(param.getMail());
            Session.setUserInfo(user);
            Session.setUser(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }catch (Exception e){
            return RestResponse.error();
        }
    }

    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> mailRegister(MailRegisterParam param) {

        if(checkVerificationNumber(param.getVerificationNumber())){
            return RestResponse.error("Verification code error");
        }

        UserBase user  = UserBase.builder().mail(TransUtils.toLowerCase(param.getMail()))
                .name(param.getName())
                .phoneNumber(param.getPhoneNumber())
                .authority((byte)1)
                .password(TransUtils.getMd5(param.getPassword())).build();

        int insert = mapper.insertSelective(user);

        if(insert == 1){
            Session.setUserInfo(user);
            Session.setUser(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }




    @Override
    public UserBase getUser(String mail) {
        mail = TransUtils.toLowerCase(mail);
        Example example = new Example(UserBase.class);
        example.createCriteria()
                .andEqualTo("mail", mail);
        List<UserBase> users = mapper.selectByExample(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            Session.setUserInfo(user);
            return user;
        }
    }

    @Override
    public UserBase getUserById(String id) {
        Example example = new Example(UserBase.class);
        example.createCriteria()
                .andEqualTo("id", id);
        List<UserBase> users = mapper.selectByExample(example);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }else{
            UserBase user = users.get(0);
            Session.setUserInfo(user);
            return user;
        }
    }


    @Override
    @SuppressWarnings("all")
    public RestResponse<UserBase> resetPassword(ResetPasswordParam param) {
        Integer verificationNumber = (Integer)Session.getSession().getAttribute("verificationNumber");

        if(verificationNumber == null || !param.getVerificationNumber().equals(verificationNumber)){
            return RestResponse.error("Verification code error");
        }

        UserBase user  = UserBase.builder().mail(TransUtils.toLowerCase(param.getMail()))
                .password(TransUtils.getMd5(param.getPassword())).build();
        Example example = new Example(UserBase.class);
        example.createCriteria().andEqualTo("mail", user.getMail());
        int update = mapper.updateByExampleSelective(user, example);
        user = getUser(user.getMail());
        if(update == 1){
            Session.setUserInfo(user);
            Session.setUser(user);
            UserThreadLocal.set(user);
            return RestResponse.ok(user);
        }else{
            return RestResponse.error();
        }
    }
}
