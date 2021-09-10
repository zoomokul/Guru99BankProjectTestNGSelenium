package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Driver;

public class DataProviderExamples {

    @Test(dataProvider="getData")
    public void setData(String username, String password)
    {
        System.out.println("your username is::"+username);
        System.out.println("your password is::"+password);
    }

    @DataProvider
    public Object[][] getData()
    {
//Rows - Number of times your test has to be repeated.
//Columns - Number of parameters in test data.
        Object[][] data = new Object[3][2];

// 1st row
        data[0][0] ="user1";
        data[0][1] = "abcdef";

// 2nd row
        data[1][0] ="user2";
        data[1][1] = "xyz";

// 3rd row
        data[2][0] ="user3";
        data[2][1] = "123456";

        return data;
    }

    @DataProvider
    public Object[][] guruLogin()
    {
//Rows - Number of times your test has to be repeated.
//Columns - Number of parameters in test data.
        Object[][] data = new Object[4][2];

// 1st row
        data[0][0] ="user1";
        data[0][1] = "abcdef";

// 2nd row
        data[1][0] ="user2";
        data[1][1] = "xyz";

// 3rd row
        data[2][0] ="user3";
        data[2][1] = "123456";

        data[3][0] ="user5";
        data[3][1] = "123456";

        return data;
    }



    @Test(dataProvider="guruLogin")
    public void guruLoginTest(String username, String password)
    {
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

        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.close();

    }

    @DataProvider
    public Object[] search()
    {

        Object[] data = new Object[3];
        data[0]="java";
        data[1]="csharp";
        data[2]="python";



        return data;
    }

    @Test(dataProvider="search")
    public void searchTest(String word)
    {
        Driver.getDriver().get("https://www.google.com.tr/");
        Driver.getDriver().findElement(By.name("q")).sendKeys(word+ Keys.ENTER);
        //Driver.getDriver().findElement(By.className("gNO89b")).click();
        System.out.println(Driver.getDriver().findElement(By.id("result-stats")).getText());



    }

}
