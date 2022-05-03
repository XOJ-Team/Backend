package com.xoj.backend.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @Author jianghanchen
 * @Date 16:51 2022/5/2
 ***/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeleniumParam {

    private String webDriver;

    private String driverLocation;

    private String mail;

    private String password;

    private String targetWeb;

    private long mills;

}
