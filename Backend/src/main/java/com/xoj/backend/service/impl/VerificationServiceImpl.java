package com.xoj.backend.service.impl;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/***
 * @Author yezhen
 * @Date 12:08 2022/3/13
 ***/

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    String sender;


    @Override
    public RestResponse<Object> sendVerificationNumber(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("XJTLU-OJ verification code");
        message.setFrom(sender);
        message.setTo(mail);
        message.setSentDate(new Date());
        message.setText(getRandomNumber());
        try{
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return RestResponse.ok();
    }

    @Override
    public String getRandomNumber() {

        Random random = new Random();
        StringBuilder result= new StringBuilder();
        for (int i=0; i<6; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
