package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(name = "uid")
    public WebElement userIdField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(name = "btnLogin")
    public WebElement loginButton;

    @FindBy(xpath = "//tr[@class='heading3']")
    public WebElement actualManagerId;
}
