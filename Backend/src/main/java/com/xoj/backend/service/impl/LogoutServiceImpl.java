package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.service.LogoutService;
import com.xoj.backend.util.AuthenticateUtils;
import org.springframework.stereotype.Service;

/***
 * @Author yezhen
 * @Date 10:52 2022/3/15
 ***/

@Service
public class LogoutServiceImpl implements LogoutService {


    @Override
    public RestResponse<Object> logout() {
        AuthenticateUtils.unAuthenticate();
        return RestResponse.ok();
    }
}
