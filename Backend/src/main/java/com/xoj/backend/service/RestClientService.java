package com.xoj.backend.service;

import org.springframework.util.MultiValueMap;

/***
 * @Author jianghanchen
 * @Date 22:09 2022/4/17
 ***/
public interface RestClientService {

    Object post(String resourceUrl, MultiValueMap<String, String> request, String username, String password);
}
