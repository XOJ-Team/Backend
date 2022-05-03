package com.xoj.backend.service.impl;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

/***
 * @Author jianghanchen
 * @Date 16:19 2022/5/2
 ***/

@Service
public class SeleniumService {

    public void login(ChromeDriver chromeDriver, String mail, String password, long mills){
        WebElement loginRegisterButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[6]");
        loginRegisterButton.click();
        sleepSecond(mills);

        WebElement inputMail = chromeDriver.findElementByXPath("//*[@id=\"normal_login_email\"]");
        inputMail.sendKeys(mail);
        sleepSecond(mills);

        WebElement inputPassword = chromeDriver.findElementByXPath("//*[@id=\"normal_login_password\"]");
        inputPassword.sendKeys(password);
        sleepSecond(mills);

        WebElement loginButton = chromeDriver.findElementByXPath("//*[@id=\"normal_login\"]/div[6]/div/div/div/button");
        loginButton.click();
        sleepSecond(mills);
    }

    public void sendMailInRegister(ChromeDriver chromeDriver, String mail, long mills){

        WebElement loginRegisterButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[6]");
        loginRegisterButton.click();
        sleepSecond(mills);

        WebElement registerButton = chromeDriver.findElementByXPath("//*[@id=\"normal_login\"]/div[6]/div/div/div/a");
        registerButton.click();
        sleepSecond(mills);

        WebElement registerMail = chromeDriver.findElementByXPath("//*[@id=\"basic_email\"]");
        registerMail.sendKeys(mail);
        sleepSecond(mills);

        WebElement sendMail = chromeDriver.findElementByXPath("//*[@id=\"basic\"]/div[3]/div/div/div/button");
        sendMail.click();
        sleepSecond(mills);

    }

    public void question(ChromeDriver chromeDriver, long mills){
        WebElement questionButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[2]");
        questionButton.click();
        sleepSecond(mills);

        WebElement viewFirstQuestion = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/main/div/div/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[2]/a");
        viewFirstQuestion.click();
        sleepSecond(mills);

        WebElement backButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/main/div/div/div[1]/div[1]/div/div/div/div");
        backButton.click();
        sleepSecond(mills);

        WebElement searchBox = chromeDriver.findElementByXPath("//*[@id=\"optionsbox\"]/div/span/span/input");
        searchBox.sendKeys("string integer Queens");
        sleepSecond(mills);

        WebElement searchButton = chromeDriver.findElementByXPath("//*[@id=\"optionsbox\"]/div/span/span/span/button");
        searchButton.click();
        sleepSecond(mills);

        WebElement tagButton = chromeDriver.findElementByXPath("//*[@id=\"tagswitch\"]/div");
        tagButton.click();
        sleepSecond(mills);
    }


    public void competition(ChromeDriver chromeDriver, long mills){
        WebElement competitionButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[3]");
        competitionButton.click();
        sleepSecond(mills);

        WebElement viewFirstCompetition = chromeDriver.findElementByXPath("//*[@id=\"123\"]");
        viewFirstCompetition.click();
        sleepSecond(mills);

        WebElement questionListButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/main/div/div/div/div[1]/div/div[2]/div[2]/div[1]/div[1]/div/div[2]");
        questionListButton.click();
        sleepSecond(mills);

        WebElement firstQuestionButton = chromeDriver.findElementByXPath("//*[@id=\"rc-tabs-0-panel-2\"]/div/div/div/ul/li[1]/div/div/h4/a");
        firstQuestionButton.click();
        sleepSecond(mills);
    }

    public void rank(ChromeDriver chromeDriver, long mills){
        WebElement rankButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[4]");
        rankButton.click();
        sleepSecond(mills);

        WebElement viewFirstUser = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/main/div/div/div[3]/div/div/div/div/div/table/tbody/tr[1]/td[1]/a");
        viewFirstUser.click();
        sleepSecond(mills);
        sleepSecond(mills);
    }


    public void about(ChromeDriver chromeDriver, long mills){
        WebElement rankButton = chromeDriver.findElementByXPath("//*[@id=\"root\"]/section/header/ul/li[5]");
        rankButton.click();
        sleepSecond(mills);
    }


    public void sleepSecond(long mills){
        try{
            sleep(mills);
        }catch (Exception ignore){}
    }

}
