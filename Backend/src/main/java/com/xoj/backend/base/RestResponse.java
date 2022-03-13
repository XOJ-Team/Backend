package com.xoj.backend.base;

import lombok.*;

/***
 * @Author yezhen
 * @Date 16:37 2022/3/12
 ***/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private T obj;
    private String comment;
    private int status;

    public static <T> RestResponse<T> ok(T obj){
        return new RestResponse<T> (obj,null,1);
    }

    public static <T> RestResponse<T>  ok(T obj, String comment){
        return new RestResponse<T> (obj,null,1);
    }

    public static <T> RestResponse<T>  ok(){
        return new RestResponse<T> (null,null,1);
    }

    public static <T> RestResponse<T>  error(T obj){ return new RestResponse<T> (obj,null,-1); }

    public static <T> RestResponse<T>  error(T obj, String comment){return new RestResponse<T> (obj,null,-1); }

    public static <T> RestResponse<T>  error(String comment){ return new RestResponse<T> (null,comment,-1); }

    public static <T> RestResponse<T>  error(){
        return new RestResponse<T> (null,null,-1);
    }



}
