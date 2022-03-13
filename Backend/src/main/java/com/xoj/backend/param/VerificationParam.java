package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/***
 * @Author yezhen
 * @Date 17:49 2022/3/13
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationParam {

    @NotNull
    private String mail;
}
