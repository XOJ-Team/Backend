package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 * @Author jianghanchen
 * @Date 22:48 2022/3/12
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
    @NotNull
    private Long id;

    private String verificationNumber;

    @NotEmpty
    private String name;

    @NotEmpty
    private String mail;

    private String phoneNumber;

    @NotEmpty
    private String password;

    private Integer score;

    private Integer ranking;

    private Byte authority;


}
