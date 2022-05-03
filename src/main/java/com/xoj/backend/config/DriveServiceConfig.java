package com.xoj.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/***
 * @Author jianghanchen
 * @Date 14:55 2022/5/2
 ***/

@Configuration
@ConfigurationProperties(prefix = "webdriver.service")
@Setter
@Getter
public class DriveServiceConfig {
    private int port = 1411;
    private String location = "F:\\chromedriver.exe";
}
