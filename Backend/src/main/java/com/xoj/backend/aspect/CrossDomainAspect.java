package com.xoj.backend.aspect;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.util.AuthenticateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * @Author jianghanchen
 * @Date 20:46 2022/4/21
 ***/

@Aspect
@Component
public class CrossDomainAspect {
    @Pointcut("execution(* com.xoj.backend.controller..*.*(..))")
    public void pointcut(){

    }
    @Before(value = "pointcut()")
    public void crossDomain(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String origin = request.getHeader("origin");
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setHeader("Access-Control-Allow-Origin",origin);
    }

}
