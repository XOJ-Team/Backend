package com.xoj.backend.service;

/***
 * @Author jianghanchen
 * @Date 22:06 2022/4/17
 ***/
public interface MailGunService {
    void sendText(String from, String to, String subject, String body);
}
