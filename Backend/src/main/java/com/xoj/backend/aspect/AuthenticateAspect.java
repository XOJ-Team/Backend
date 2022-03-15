package com.xoj.backend.aspect;

/***
 * @Author yezhen
 * @Date 15:20 2022/3/15
 ***/

import com.xoj.backend.util.AuthenticateUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class AuthenticateAspect {


    @Pointcut("@annotation(com.xoj.backend.notation.RequirePermission)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void checkPermission() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(AuthenticateUtils.isNotAuthenticated()){
//            response.sendRedirect();
            System.out.println("未认证");
        }

    }
}