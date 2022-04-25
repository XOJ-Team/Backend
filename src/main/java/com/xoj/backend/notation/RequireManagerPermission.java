package com.xoj.backend.notation;

import java.lang.annotation.*;

/***
 * @Author jianghanchen
 * @Date 14:16 2022/3/20
 ***/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequireProPermission
public @interface RequireManagerPermission{
    String value() default "";
}