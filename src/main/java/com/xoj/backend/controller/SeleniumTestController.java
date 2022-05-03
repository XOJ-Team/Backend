package com.xoj.backend.controller;

import com.xoj.backend.param.SeleniumParam;
import com.xoj.backend.service.impl.SeleniumService;
import io.swagger.annotations.Api;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/***
 * @Author jianghanchen
 * @Date 14:57 2022/5/2
 ***/

@RestController
@Api(value = "Regression testing. For local use only. Need to be deleted when XOJ goes live!")
public class SeleniumTestController {
    private ChromeDriver chromeDriver;

    @Autowired
    SeleniumService seleniumService;

    @PostMapping("test")
    public void test(@RequestBody SeleniumParam param) {
        System.setProperty(param.getWebDriver(), param.getDriverLocation());
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.get(param.getTargetWeb());
        chromeDriver.manage().window().maximize();
        try{
            seleniumService.login(chromeDriver,param.getMail(),param.getPassword(),param.getMills());
            seleniumService.question(chromeDriver,param.getMills());
            seleniumService.competition(chromeDriver,param.getMills());
            seleniumService.rank(chromeDriver,param.getMills());
            seleniumService.about(chromeDriver,param.getMills());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
