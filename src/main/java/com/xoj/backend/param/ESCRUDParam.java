package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @Author jianghanchen
 * @Date 14:43 2022/3/27
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ESCRUDParam {
    private String index;

    private String field;

    private String id;

    private Object obj;
}
