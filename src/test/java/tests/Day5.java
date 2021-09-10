package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.Util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Day5 {





    @DataProvider
    public Object[][] guruLogin()
    {
//Rows - Number of times your test has to be repeated.
//Columns - Number of parameters in test data.
        Object[][] data = new Object[4][2];

// 1st row
        data[0][0] ="mngr351648";
        data[0][1] = "Ynugyqu";

// 2nd row
        data[1][0] ="user2";
        data[1][1] = "xyz";

// 3rd row
        data[2][0] ="user3";
        data[2][1] = "123456";
// 4rd row
        data[3][0] ="user5";
        data[3][1] = "123456";

        return data;
    }



    @Test(dataProvider="guruLogin")
    public void guruLoginTest(String username, String password) throws IOException {
        WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
        driver.get("http://www.demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(username);

        // Enter Password
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        // Click Login
       driver.findElement(By.name("btnLogin")).click();



        // try catch block
        try {
            Assert.assertEquals(driver.switchTo().alert().getText(),"User or Password is not valid");
            System.out.println(driver.switchTo().alert().getText());

            driver.switchTo().alert().accept();

        }
        catch (NoAlertPresentException Ex){

            System.out.println(driver.findElement(By.tagName("tbody")).getText());


            /*

            ///// Standart take screen shot //////////
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("C:\\projectScreenshots\\page.jpeg"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            */
            /*
            ///// Standart take screen shot second way//////////
            // Locate the element on the web page
            WebElement logo = driver.findElement(By.tagName("tbody"));

            // Get screenshot of the visible part of the web page
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Convert the screenshot into BufferedImage
            BufferedImage fullScreen = ImageIO.read(screenshot);

            //Find location of the webelement logo on the page
            Point location = logo.getLocation();

            //Find width and height of the located element logo
            int width = logo.getSize().getWidth();
            int height = logo.getSize().getHeight();

            //cropping the full image to get only the logo screenshot
            BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(),
                    width, height);
            ImageIO.write(logoImage, "png", screenshot);

            //Save cropped Image at destination location physically.
            FileUtils.copyFile(screenshot, new File("C:\\projectScreenshots\\particularElementScreenshot.PNG"));


            ///// Standart take screen shot third way//////////
            // capture screenshot with getScreenshotAs() of the WebElement class
            File f = logo.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(f, new File("C:\\projectScreenshots\\logoScreeshot.png"));

*/



        }

        //System.out.println(driver.switchTo().alert().getText());
        //driver.switchTo().alert().accept();
        driver.close();

    }





}
