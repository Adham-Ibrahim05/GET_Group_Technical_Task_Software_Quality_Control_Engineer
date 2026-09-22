package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Login_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC01_Login extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String InvalidUsername = GetJsonData("Dataset","Invalid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Inventory_Title = GetJsonData("Dataset","Inventory Title");
    protected String Error_Message = GetJsonData("Dataset","Error Message");

    @Test
    public void ValidLogin(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(Inventory_Title));
    }

    @Test
    public void InvalidLogin(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(InvalidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isLoginFailed(Error_Message));
    }
}