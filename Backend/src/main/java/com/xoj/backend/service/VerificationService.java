package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;

/***
 * @Author jianghanchen
 * @Date 12:05 2022/3/13
 ***/
public interface VerificationService{
    RestResponse<Object> sendVerificationNumber(String mail);

    RestResponse<Object> sendVerificationNumberResetPassword(String mail);

    String getRandomNumber();
}
