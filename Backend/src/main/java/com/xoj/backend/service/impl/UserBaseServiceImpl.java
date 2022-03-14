package com.xoj.backend.service.impl;

import com.xoj.backend.common.UserThreadLocal;
import com.xoj.backend.entity.User;
import com.xoj.backend.service.UserBaseService;
import org.springframework.stereotype.Service;

/**
 * @author 1iin
 */
@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Override
    public User getCurrentUser() {
        return UserThreadLocal.get();
    }
}
