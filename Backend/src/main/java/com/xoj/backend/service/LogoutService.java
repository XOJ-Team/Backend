package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;

/***
 * @Author yezhen
 * @Date 10:51 2022/3/15
 ***/

public interface LogoutService {
    RestResponse<Object> logout();
}
