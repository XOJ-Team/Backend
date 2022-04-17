package com.xoj.backend.service.impl;

import com.xoj.backend.service.MailGunService;
import com.xoj.backend.service.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
public class MailGunServiceImpl implements MailGunService {

    private RestClientService springRestClient;
    private String password;
    private String messagesUrl;
    private String username;

    @Autowired
    public MailGunServiceImpl(RestClientService springRestClient, String mailGunAPIMessagesUrl, String mailGunAPIUsername,
                              String mailGunAPIPassword) {
        this.springRestClient = springRestClient;
        this.username = mailGunAPIUsername;
        this.password = mailGunAPIPassword;
        this.messagesUrl = mailGunAPIMessagesUrl;
    }

    @Override
    public void sendText(String from, String to, String subject, String body) {
        MultiValueMap<String, String> map = getPostRequestObject(from, to, subject);
        map.add("text", body);
        sendEmail(map);

    }


    private void sendEmail(MultiValueMap<String, String> map) {
        try{
            this.springRestClient.post(this.messagesUrl, map, this.username, this.password);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private MultiValueMap<String, String> getPostRequestObject(String from, String to, String subject) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("from", from);
        map.add("to", to);
        map.add("subject", subject);
        return map;
    }

}