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
    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;
}
