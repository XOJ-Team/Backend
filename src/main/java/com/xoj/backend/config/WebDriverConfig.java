package com.xoj.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.remote.BrowserType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/***
 * @Author jianghanchen
 * @Date 14:53 2022/5/2
 ***/
@Configuration
@ConfigurationProperties(prefix = "webdriver")
@Setter
@Getter
public class WebDriverConfig {
    private String osName = System.getProperty("os.name");
    private String browser = BrowserType.CHROME;
    private Boolean isRemote = false;
    private Map<String, Object> capabilities;
    private String[] listeners;
}

