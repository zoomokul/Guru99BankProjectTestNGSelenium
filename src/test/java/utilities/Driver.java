package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){
        //Driver classindan obje olusturulmasini engellemek icin
        //default constructor i yazip access modifier ini private yapariz
        //bunun gibi kendisinden obje olusturulmasi kontrol altina alinan classlara
        //SINGLETON CLASS denir
    }

    private static WebDriver driver;

    public static WebDriver getDriver(){

        if(driver==null){

            switch (ConfigReader.getProperty("browser")){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "opera" :
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                case "safari" :
                    WebDriverManager.getInstance(SafariDriver.class);
                    driver = new SafariDriver();
                    break;

            }

        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.close();
            driver=null;
        }
    }

}
