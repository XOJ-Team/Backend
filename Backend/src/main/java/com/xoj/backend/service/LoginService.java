package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.param.MailLoginParam;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.param.UserParam;
import com.xoj.backend.param.ResetPasswordParam;

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
}
