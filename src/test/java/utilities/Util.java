package utilities;



import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class Util {

    public static WebDriver driver;

    // You can change the information of your data file here
    public static final String FILE_PATH = "testData.xls"; // File Path
    public static final String SHEET_NAME = "Data"; // Sheet name
    public static final String TABLE_NAME = "testData"; // Name of data table

    public static final String BASE_URL = "http://www.demo.guru99.com/";
    public static final String TEST_URL="http://www.demo.guru99.com/V4/";

    // Expected output
    public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
    public static final String EXPECT_ERROR = "User or Password is not valid";


    public static String username, password;
    public static String actualTitle;
    public  static String actualBoxtitle;




    /**
     * This method reads the data from the Sheet name "Data" of file
     * "testData.xls"
     *
     *
     * @param xlFilePath
     *            : Path of excel file
     * @param sheetName
     *            : Sheet name which contains test data
     * @param tableName
     *            : Table name is used to mark the start and end position of the
     *            test data table. The method will find the cell which contains
     *            the table name to find position of data table
     * @return a 2 dimensions array to store all the test data read from excel
     * @throws Exception
     */

    /////////////////
    public static void getDataFromExcel2(String xlFilePath,
                                              String sheetName, String tableName) throws Exception {
        // Declare a 2 dimensions array to store all the test data read from
        // excel
        String[][] tabArray = null;

        // get the workbook file. Provide the path of excel file
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        // get the sheet name
        Sheet sheet = workbook.getSheet(sheetName);

        int startRow, startCol, endRow, endCol, ci, cj;

        // find cell position which contain first appear table name
        Cell tableStart = sheet.findCell(tableName);
        // Row position of FIRST appear table name
        startRow = tableStart.getRow();
        // Col position of FIRST appear table name
        startCol = tableStart.getColumn();

        // find cell position which contain last appear table name
        Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
                100, 64000, false);
        // Row position of LAST appear table name
        endRow = tableEnd.getRow();
        // Col position of LAST appear table name
        endCol = tableEnd.getColumn();

        tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
        ci = 0;

        // Store all data in an array
        for (int i = startRow + 1; i < endRow; i++, ci++) {
            cj = 0;
            for (int j = startCol + 1; j < endCol; j++, cj++) {
                //Get content of each cell and store to each array element.
                tabArray[ci][cj] = sheet.getCell(j, i).getContents();
            }
        }

        //Testing for all parameters stored in the Excel File
        for (int i = 0; i < tabArray.length; i++) {
            username = tabArray[i][0]; // get username
            password = tabArray[i][1]; // get password

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get(TEST_URL);
            // Enter username
            driver.findElement(By.name("uid")).clear();
            driver.findElement(By.name("uid")).sendKeys(username);

            // Enter Password
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys(password);

            // Click Login
            driver.findElement(By.name("btnLogin")).click();

            /* Determine Pass Fail Status of the Script
             * If login credentials are correct,  Alert(Pop up) is NOT present. An Exception is thrown and code in catch block is executed
             * If login credentials are invalid, Alert is present. Code in try block is executed
             */
            try{

                Alert alt = driver.switchTo().alert();
                actualBoxtitle = alt.getText(); // get content of the Alter Message
                alt.accept();
                if (actualBoxtitle.contains(EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
                    System.out.println("Test case SS[" + i + "]: Passed");
                } else {
                    System.out.println("Test case SS[" + i + "]: Failed");
                }
            }
            catch (NoAlertPresentException Ex){
                actualTitle = driver.getTitle();
                // On Successful login compare Actual Page Title with Expected Title
                if (actualTitle.contains(EXPECT_TITLE)) {
                    System.out.println("Test case SS[" + i + "]: Passed");
                } else {
                    System.out.println("Test case SS[" + i + "]: Failed");
                }

            }
            driver.close();
        }
    }

    ////////////////////////////

    public static String[][] getDataFromExcel(String xlFilePath,
                                              String sheetName, String tableName) throws Exception {
        // Declare a 2 dimensions array to store all the test data read from
        // excel
        String[][] tabArray = null;

        // get the workbook file. Provide the path of excel file
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        // get the sheet name
        Sheet sheet = workbook.getSheet(sheetName);

        int startRow, startCol, endRow, endCol, ci, cj;

        // find cell position which contain first appear table name
        Cell tableStart = sheet.findCell(tableName);
        // Row position of FIRST appear table name
        startRow = tableStart.getRow();
        // Col position of FIRST appear table name
        startCol = tableStart.getColumn();

        // find cell position which contain last appear table name
        Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
                100, 64000, false);
        // Row position of LAST appear table name
        endRow = tableEnd.getRow();
        // Col position of LAST appear table name
        endCol = tableEnd.getColumn();

        tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
        ci = 0;

        // Store all data in an array
        for (int i = startRow + 1; i < endRow; i++, ci++) {
            cj = 0;
            for (int j = startCol + 1; j < endCol; j++, cj++) {
                //Get content of each cell and store to each array element.
                tabArray[ci][cj] = sheet.getCell(j, i).getContents();
            }
        }

        return (tabArray);
    }






}
