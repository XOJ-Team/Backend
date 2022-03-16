package com.xoj.backend.service.impl;

import com.xoj.backend.util.UserThreadLocal;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.service.UserBaseService;
import org.springframework.stereotype.Service;

/**
 * @author 1iin
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Override
    public UserBase getCurrentUser() {
        return UserThreadLocal.get();
    }
}
