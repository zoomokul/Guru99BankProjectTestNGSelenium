package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Day1 {

    @Test
    public void day1Test(){
        LoginPage loginPage=new LoginPage();
        Driver.getDriver().get(ConfigReader.getProperty("gr_url"));

        loginPage.userIdField.sendKeys(ConfigReader.getProperty("gr_user_id"));
        loginPage.passwordField.sendKeys(ConfigReader.getProperty("gr_password"));
        loginPage.loginButton.click();
        Assert.assertEquals(loginPage.actualManagerId.getText(),ConfigReader.getProperty("expected_manager_id"));

        Driver.closeDriver();
    }
}
