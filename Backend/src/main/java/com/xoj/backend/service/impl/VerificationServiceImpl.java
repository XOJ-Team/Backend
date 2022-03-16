package com.xoj.backend.service.impl;


import com.xoj.backend.base.RestResponse;
import com.xoj.backend.base.Session;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.service.LoginService;
import com.xoj.backend.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    @Autowired
    LoginService loginService;

    @Value(value = "${spring.mail.username}")
    String sender;


    @Override
    public RestResponse<Object> sendVerificationNumber(String mail) {
        HttpSession session = Session.getSession();
        String verificationNumber = getRandomNumber();
        session.setAttribute("verificationNumber",verificationNumber);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("XJTLU-OJ verification code");
        message.setFrom(sender);
        message.setTo(mail);
        message.setSentDate(new Date());
        message.setText("Hi,\n" + " \n" + "Welcome！\n" + " \n" +
                "Thank you for registering XOJ. We hope you enjoy it. Join us and improve your coding ability now!" +
                "  To complete the register, please enter the verification code below.\n" + " \n" +
                "Verification Code:"+verificationNumber+" \n" + " \n" + "Thanks,\n" + "XOJ Developer Team");
        try{
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return RestResponse.ok();
    }

    @Override
    public RestResponse<Object> sendVerificationNumberResetPassword(String mail) {

        UserBase user = loginService.getUser(mail);
        if(user == null){
            return RestResponse.error("User not find");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("XJTLU-OJ verification code");
        message.setFrom(sender);
        message.setTo(mail);
        message.setSentDate(new Date());
        message.setText("Hi,\n" + " \n" + "Welcome！\n" + " \n" +
                "Thank you for visiting XOJ. We hope you enjoy it." +
                "  To reset the password, please enter the verification code below.\n" + " \n" +
                "Verification code:"+getRandomNumber()+" \n" + " \n" + "Thanks,\n" + "XOJ Developer Team");
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
