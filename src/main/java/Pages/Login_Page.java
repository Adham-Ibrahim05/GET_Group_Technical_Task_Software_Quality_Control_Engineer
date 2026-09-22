package Pages;

import Utilities.GeneralUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Page {
    private final WebDriver driver;
    private final By Username = By.id("user-name");
    private final By Password = By.id("password");
    private final By LoginButton = By.id("login-button");
    private final By InventoryPageTitle = By.cssSelector("span[data-test='title']");
    private final By LoginErrorMessage = By.cssSelector("h3[data-test='error']");

    public Login_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Login_Page EnterUsername(String username)
    {
        GeneralUtility.SendData(driver,Username,username);
        return this;
    }

    public Login_Page EnterPassword(String password)
    {
        GeneralUtility.SendData(driver,Password,password);
        return this;
    }

    public void ClickOnLogin()
    {
        GeneralUtility.Click_OnElement(driver,LoginButton);
    }

    public boolean isInventoryPageDisplayed(String expectedTitle)
    {
        return GeneralUtility.GetText(driver, InventoryPageTitle).equals(expectedTitle);
    }

    public boolean isLoginFailed(String expected)
    {
        return GeneralUtility.GetText(driver, LoginErrorMessage).equals(expected);
    }
}