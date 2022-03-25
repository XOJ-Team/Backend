package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.*;

/***
 * @Author jianghanchen
 * @Date 22:45 2022/3/12
 ***/
public interface  LoginService {
    RestResponse<UserBase> normalLogin(NormalLoginParam param);

    RestResponse<UserBase> mailLogin(MailLoginParam param);

    UserBase getUser(String mail);

    RestResponse<UserBase> resetPassword(ResetPasswordParam param);

    boolean checkVerificationNumber(String verificationNumber);

    RestResponse<UserBase> mailRegister(MailRegisterParam param);
}
