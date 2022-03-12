package com.xoj.backend.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 * @Author yezhen
 * @Date 17:04 2022/3/12
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NormalLoginParam {

    @NotNull
    private String mail;

    @NotNull
    private String password;
}
