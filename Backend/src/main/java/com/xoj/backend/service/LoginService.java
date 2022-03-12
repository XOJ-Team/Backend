package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.User;
import com.xoj.backend.param.NormalLoginParam;
import com.xoj.backend.param.MailLoginParam;

/***
 * @Author yezhen
 * @Date 22:45 2022/3/12
 ***/
public interface  LoginService {
    RestResponse<User> normalLogin(NormalLoginParam param);

    RestResponse<User> mailLogin(MailLoginParam param);
}
