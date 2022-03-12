package com.xoj.backend.util;

import org.springframework.util.DigestUtils;

/***
 * @Author yezhen
 * @Date 22:57 2022/3/12
 ***/
public class TransUtils {
    public static String getMd5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
