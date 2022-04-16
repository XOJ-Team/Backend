package com.xoj.backend.base;

import com.xoj.backend.entity.UserBase;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/***
 * @Author jianghanchen
 * @Date 12:09 2022/3/13
 ***/
public class Session {

    public static HttpSession getSession(){
        HttpSession session;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession();
        session.setMaxInactiveInterval(5184000);
        return session;
    }

    public static UserBase getCurrentUser(){
        return (UserBase)Session.getSession().getAttribute("user");
    }

    public static String getCurrentUserVerificationNumber(){
        return (String)Session.getSession().getAttribute("verificationNumber");
    }

    public static String getCurrentUserMail(){
        return (String)Session.getSession().getAttribute("mail");
    }

    public static String getCurrentUserName(){
        return (String)Session.getSession().getAttribute("name");
    }

    public static Byte getCurrentUserAuthority(){
        return (Byte)Session.getSession().getAttribute("authority");
    }

    public static void setUserInfo(UserBase user){
        HttpSession session = getSession();
        session.setAttribute("id",user.getId());
        session.setAttribute("name",user.getName());
        session.setAttribute("mail",user.getMail());
        session.setAttribute("authority",user.getAuthority());
    }

    public static void setUser(UserBase user){
        HttpSession session = getSession();
        session.setAttribute("user",user);
    }

    public static void removeUser(){
        HttpSession session = getSession();
        session.removeAttribute("user");
    }
    public static void removeUserInfo(){
        HttpSession session = getSession();
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("authority");
    }

}
