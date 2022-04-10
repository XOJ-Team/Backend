package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author jianghanchen
 * @Date 20:59 2022/4/10
 ***/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserInfoParam {

    private String verificationNumber;

    private String name;

    @NotNull
    private String mail;

    private String phoneNumber;

    private String password;
}
