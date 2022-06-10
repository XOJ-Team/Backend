package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.notation.RequirePermission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Author jianghanchen
 * @Date 17:23 2022/3/20
 ***/
@RestController
public class AuthenticateController {

    @RequestMapping(value = "/noAuthentication", method = RequestMethod.GET)
    public RestResponse<?> changeInfo(Object obj) {return RestResponse.error("no authentication"); }
    @RequestMapping(value = "/noAuthentication", method = RequestMethod.POST)
    public RestResponse<?> changeInfo2(Object obj) {
        return RestResponse.error("no authentication");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @RequirePermission
    public RestResponse<?> authenticate(Object obj) {
        return RestResponse.ok("Session ID is valid");
    }

}
