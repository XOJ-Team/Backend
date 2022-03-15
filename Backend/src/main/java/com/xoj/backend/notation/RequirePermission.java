package com.xoj.backend.notation;

import java.lang.annotation.*;

/***
 * @Author yezhen
 * @Date 15:30 2022/3/15
 ***/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePermission {
    String value() default "";
}