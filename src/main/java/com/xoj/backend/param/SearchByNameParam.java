package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author jianghanchen
 * @Date 14:33 2022/3/27
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchByNameParam {
    private String name;

    private String context;
}
