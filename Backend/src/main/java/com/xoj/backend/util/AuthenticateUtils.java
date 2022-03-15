package com.xoj.backend.util;

import com.xoj.backend.base.Session;

import javax.servlet.http.HttpSession;

/***
 * @Author yezhen
 * @Date 10:46 2022/3/15
 ***/
public class AuthenticateUtils {

    public static boolean isAuthenticated(){
        HttpSession session = Session.getSession();
        Integer authenticated = (Integer)session.getAttribute("authenticated");
        if(authenticated != null && authenticated.equals(1)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isNotAuthenticated(){
        HttpSession session = Session.getSession();
        Integer authenticated = (Integer)session.getAttribute("authenticated");
        if(authenticated != null && authenticated.equals(1)){
            return false;
        }else {
            return true;
        }
    }

    public static void authenticate(Integer authority){
        HttpSession session = Session.getSession();
        session.setAttribute("authenticated",authority);
    }

    public static void unAuthenticate(){
        HttpSession session = Session.getSession();
        session.removeAttribute("authenticated");
    }
}
