package com.xoj.backend.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @Author yezhen
 * @Date 16:48 2022/3/12
 ***/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestRequest<T> {
    private T parameter;
}
