package com.xoj.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 1iin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonVO<T> {

    private String msg;

    private T data;
}
