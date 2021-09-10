package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Util;

import java.util.concurrent.TimeUnit;

public class Day4 {

    @DataProvider(name = "GuruTest")
    public Object[][] testData() throws Exception {
        return Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME,
                Util.TABLE_NAME);
    }
    @Test(dataProvider = "GuruTest")
    public void testCase04(String username, String password) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(Util.TEST_URL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String actualTitle;
        String actualBoxMsg;
        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();



        try{

            Alert alt = driver.switchTo().alert();
            actualBoxMsg = alt.getText(); // get content of the Alter Message
            alt.accept();
            // Compare Error Text with Expected Error Value
            Assert.assertEquals(actualBoxMsg,Util.EXPECT_ERROR);


        }
        catch (NoAlertPresentException Ex){
            actualTitle = driver.getTitle();
            // On Successful login compare Actual Page Title with Expected Title
            Assert.assertEquals(actualTitle,Util.EXPECT_TITLE);
        }
        driver.close();
    }


}
