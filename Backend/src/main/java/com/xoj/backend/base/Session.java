package com.xoj.backend.base;

import com.xoj.backend.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/***
 * @Author yezhen
 * @Date 12:09 2022/3/13
 ***/
public class Session {

    public static HttpSession getSession(){
        HttpSession session;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();
        return session;
    }


    public static void setUserInfo(User user){
        HttpSession session = getSession();
        session.setAttribute("id",user.getId());
        session.setAttribute("name",user.getName());
        session.setAttribute("mail",user.getMail());
        session.setAttribute("authority",user.getAuthority());
    }

    public static void setUser(User user){
        HttpSession session = getSession();
        session.setAttribute("user",user);
    }

    public static void removeUser(){
        HttpSession session = getSession();
        session.removeAttribute("user");
    }
    public static void removeUserInfo(User user){
        HttpSession session = getSession();
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("authority");
    }

}
