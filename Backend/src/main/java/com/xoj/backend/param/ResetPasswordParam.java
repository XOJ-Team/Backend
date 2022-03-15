package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author yezhen
 * @Date 11:12 2022/3/15
 ***/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordParam {
    @NotNull
    private String verificationNumber;

    @NotNull
    private String mail;

    @NotNull
    private String password;
}
