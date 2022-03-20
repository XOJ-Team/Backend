package com.xoj.backend.aspect;

/***
 * @Author jianghanchen
 * @Date 15:20 2022/3/15
 ***/

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.notation.RequirePermission;
import com.xoj.backend.util.AuthenticateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class AuthenticateAspect {


    @Around("@annotation(com.xoj.backend.notation.RequirePermission)")
    public Object checkPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(AuthenticateUtils.isNotAuthenticated()){
            return RestResponse.error("no authentication");
        }
        return proceedingJoinPoint.proceed();
    }

    @Around("@annotation(com.xoj.backend.notation.RequireProPermission)")
    public Object checkProPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(AuthenticateUtils.isNotProAuthenticated()){
            return RestResponse.error("no authentication");
        }
        return proceedingJoinPoint.proceed();
    }


    @Around("@annotation(com.xoj.backend.notation.RequireManagerPermission)")
    public Object checkManagerPermission(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if(AuthenticateUtils.isNotManagerAuthenticated()){
            return RestResponse.error("no authentication");
        }
        return proceedingJoinPoint.proceed();
    }
}