package com.xoj.backend.notation;

import java.lang.annotation.*;

/***
 * @Author jianghanchen
 * @Date 14:16 2022/3/20
 ***/

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequirePermission
public @interface RequireProPermission{
    String value() default "";
}