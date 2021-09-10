package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;


import static utilities.Util.*;

public class Day3 {

    @Test
    public void day3Test1(){
        LoginPage loginPage=new LoginPage();
        Driver.getDriver().get(ConfigReader.getProperty("gr_url"));

        loginPage.userIdField.sendKeys(ConfigReader.getProperty("gr_user_id"));
        loginPage.passwordField.sendKeys(ConfigReader.getProperty("gr_password"));
        loginPage.loginButton.click();
        Assert.assertEquals(loginPage.actualManagerId.getText(),ConfigReader.getProperty("expected_manager_id"));
        Assert.assertEquals(Driver.getDriver().getTitle(),ConfigReader.getProperty("expected_homepage_title"));
        Driver.closeDriver();
    }






}
