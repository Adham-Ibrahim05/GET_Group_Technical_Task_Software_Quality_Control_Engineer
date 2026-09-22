package Pages;

import Utilities.GeneralUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout_Page {
    private final WebDriver driver;
    private final By SideMenu = By.id("react-burger-menu-btn");
    private final By LogoutButton = By.id("logout_sidebar_link");
    private final By LoginPageTitle = By.id("login_credentials");

    public Logout_Page(WebDriver driver) {
        this.driver = driver;
    }

    public void Logout()
    {

        GeneralUtility.Click_OnElement(driver,SideMenu);
        GeneralUtility.Click_OnElement(driver,LogoutButton);
    }

    public boolean isLoginPageDisplayed(String expectedTitle)
    {
        return GeneralUtility.GetText(driver, LoginPageTitle).contains(expectedTitle);
    }
}