package com.xoj.backend.util;

import com.xoj.backend.base.Session;
import com.xoj.backend.entity.UserBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/***
 * @Author jianghanchen
 * @Date 10:46 2022/3/15
 ***/
public class AuthenticateUtils {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUtils.class);

    public static boolean isAuthenticated(){
        HttpSession session = Session.getSession();
        Integer authenticated = (Integer)session.getAttribute("authority");
        if(authenticated != null && authenticated >= 1){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isProAuthenticated(){
        HttpSession session = Session.getSession();
        Integer authenticated = (Integer)session.getAttribute("authority");
        if(authenticated != null && authenticated >= 2){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isManagerAuthenticated(){
        HttpSession session = Session.getSession();
        Integer authenticated = (Integer)session.getAttribute("authority");
        if(authenticated != null && authenticated >= 3){
            return true;
        }else {
            return false;
        }
    }



    public static boolean isNotAuthenticated(){
        HttpSession session = Session.getSession();
        Byte authenticated = ((UserBase) session.getAttribute("user")).getAuthority();
        if(authenticated != null && authenticated >= 1){
            return false;
        }else {
            return true;
        }
    }

    public static boolean isNotProAuthenticated(){
        HttpSession session = Session.getSession();
        Byte authenticated = ((UserBase) session.getAttribute("user")).getAuthority();
        if(authenticated != null && authenticated >= 2){
            return false;
        }else {
            return true;
        }
    }

    public static boolean isNotManagerAuthenticated(){
        HttpSession session = Session.getSession();
        Byte authenticated = ((UserBase) session.getAttribute("user")).getAuthority();
        if(authenticated != null && authenticated >= 3){
            return false;
        }else {
            if (null == authenticated) {
                logger.info("authentication is null");
                return true;
            }
            logger.info(authenticated.toString());
            logger.info(session.getAttribute("user").toString());
            return true;
        }
    }


    public static void authenticate(Integer authority){
        HttpSession session = Session.getSession();
        session.setAttribute("authority",authority);
    }

    public static void unAuthenticate(){
        Session.removeUser();
        Session.removeUserInfo();
    }
}
