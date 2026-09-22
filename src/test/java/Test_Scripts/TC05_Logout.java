package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Login_Page;
import Pages.Logout_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC05_Logout extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Login_Title = GetJsonData("Dataset","Login Title");

    @Test
    public void Logout(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();

        Logout_Page logoutPage = new Logout_Page(GetDriver());
        logoutPage.Logout();
        Assert.assertTrue(logoutPage.isLoginPageDisplayed(Login_Title));

    }

}