package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author jianghanchen
 * @Date 15:36 2022/3/25
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailRegisterParam {


    private String verificationNumber;

    private String name;

    @NotNull
    private String mail;

    private String phoneNumber;

    private String password;
}
