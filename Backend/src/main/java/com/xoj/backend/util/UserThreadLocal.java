package com.xoj.backend.util;

import com.xoj.backend.base.Session;
import com.xoj.backend.entity.UserBase;

import javax.servlet.http.HttpSession;

public class UserThreadLocal {

    private static ThreadLocal<UserBase> userThread = new ThreadLocal<>();

    public static void set(UserBase user) { userThread.set(user);
    }

    public static void set(){
        HttpSession session = Session.getSession();
        UserBase user = (UserBase) session.getAttribute("user");
        if(user != null){
            userThread.set(user);
        }
    }

    public static UserBase get() {
        return userThread.get();
    }

    public static void remove() {
        userThread.remove();
    }
}