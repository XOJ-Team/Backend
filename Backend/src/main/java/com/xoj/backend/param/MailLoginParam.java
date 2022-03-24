package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author jianghanchen
 * @Date 13:02 2022/3/24
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailLoginParam {


    private String verificationNumber;

    private String name;

    @NotNull
    private String mail;

    private String phoneNumber;

    private String password;

}
