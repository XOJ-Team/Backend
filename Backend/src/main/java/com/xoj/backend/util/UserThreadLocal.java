package com.xoj.backend.util;

import com.xoj.backend.entity.User;

public class UserThreadLocal {

    private static ThreadLocal<User> userThread = new ThreadLocal<>();

    public static void set(User user) {
        userThread.set(user);
    }

    public static User get() {
        return userThread.get();
    }

    public static void remove() {
        userThread.remove();
    }
}