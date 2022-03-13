package com.xoj.backend.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 * @Author yezhen
 * @Date 12:09 2022/3/13
 ***/
public class Session {
    private static volatile HttpSession session;
    public static HttpSession getSession(){
        if(session == null){
            synchronized (Session.class){
                if(session == null){
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    session = request.getSession();
                    return session;
                }

            }
        }
        return session;
    }

}
