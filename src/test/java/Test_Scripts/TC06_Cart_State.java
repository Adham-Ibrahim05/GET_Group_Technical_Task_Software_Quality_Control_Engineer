package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Cart_Page;
import Pages.Inventory_Page;
import Pages.Login_Page;
import Pages.Logout_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC06_Cart_State extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Inventory_Title = GetJsonData("Dataset","Inventory Title");
    protected String Product1 = GetJsonData("Dataset","Product 1");
    protected int Cart_Count = Integer.parseInt(GetJsonData("Dataset","Cart Count - 1 Product"));

    @Test
    public void Cart_State_During_Navigation(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(Inventory_Title));

        Inventory_Page inventoryPage = new Inventory_Page(GetDriver());
        inventoryPage.AddProductToCart(Product1);
        Assert.assertEquals(inventoryPage.GetCartBadgeCount(),Cart_Count);

        inventoryPage.OpenCart();
        Cart_Page cartPage = new Cart_Page(GetDriver());
        Assert.assertTrue(cartPage.isProductDisplayed(Product1));
        cartPage.ContinueShopping();
        Assert.assertEquals(cartPage.GetCartBadgeCount(),Cart_Count);

        inventoryPage.OpenCart();
        Assert.assertTrue(cartPage.isProductDisplayed(Product1));

        Logout_Page logoutPage = new Logout_Page(GetDriver());
        logoutPage.Logout();

        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();

        Assert.assertEquals(inventoryPage.GetCartBadgeCount(),Cart_Count);
    }

}