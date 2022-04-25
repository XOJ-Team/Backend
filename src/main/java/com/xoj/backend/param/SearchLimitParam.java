package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @Author jianghanchen
 * @Date 14:30 2022/4/19
 ***/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchLimitParam {

    private Integer from;

    private Integer size;

    private String value;
}
