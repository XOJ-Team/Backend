package com.xoj.backend.common;

import com.xoj.backend.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/***
 * @Author yezhen
 * @Date 22:35 2022/3/14
 ***/
public class UserAuthProvider implements AuthenticationProvider {

    @Autowired
    LoginServiceImpl loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
