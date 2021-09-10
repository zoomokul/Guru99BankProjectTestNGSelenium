package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static utilities.Util.*;
import static utilities.Util.TABLE_NAME;

public class ReadExcelWithMethod {


    @Test void test02() throws Exception {
        getDataFromExcel2(FILE_PATH,
                SHEET_NAME, TABLE_NAME);
    }
}
