package com.xoj.backend.controller;

import com.xoj.backend.base.RestResponse;
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
    public void changeInfo(Object obj) {
//        ServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        ServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        if(AuthenticateUtils.isNotProAuthenticated()){
////            response.sendRedirect();
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/noAuthentication");
//            try{
//
//                requestDispatcher.forward(request,response);
//            }catch (Exception e){
//                System.out.println();
//            }
//            System.out.println("未认证2");
//        }
    }
    @RequestMapping(value = "/noAuthentication", method = RequestMethod.POST)
    public RestResponse<?> changeInfo2(Object obj) {
        return RestResponse.error("no authentication");
    }

}
