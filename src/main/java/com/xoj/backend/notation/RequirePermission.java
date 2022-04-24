package com.xoj.backend.notation;

import java.lang.annotation.*;

/***
 * @Author jianghanchen
 * @Date 15:30 2022/3/15
 ***/

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePermission {
    String value() default "";
}