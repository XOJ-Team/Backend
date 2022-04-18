package com.xoj.backend.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/***
 * @Author jianghanchen
 * @Date 16:33 2022/4/18
 ***/
public class CookieUtils {


    public static void setCookie(HttpServletResponse response,String mail) {
        Cookie cookie = new Cookie("mail",mail);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(5184000);
        response.addCookie(cookie);
    }

}
