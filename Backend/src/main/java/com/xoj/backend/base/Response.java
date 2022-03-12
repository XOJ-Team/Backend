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
public class Response<T> {
    private T obj;
    private String comment;
    private int status;
}
