package com.xoj.backend.util;

import com.xoj.backend.entity.UserBase;

public class UserThreadLocal {

    private static ThreadLocal<UserBase> userThread = new ThreadLocal<>();

    public static void set(UserBase user) {
        userThread.set(user);
    }

    public static UserBase get() {
        return userThread.get();
    }

    public static void remove() {
        userThread.remove();
    }
}