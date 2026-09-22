package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Cart_Page;
import Pages.Checkout_Page;
import Pages.Inventory_Page;
import Pages.Login_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC04_E2E_Checkout extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Inventory_Title = GetJsonData("Dataset","Inventory Title");
    protected String Product1 = GetJsonData("Dataset","Product 1");
    protected String Product2 = GetJsonData("Dataset","Product 2");
    protected String FirstName = GetJsonData("Dataset","First Name");
    protected String LastName = GetJsonData("Dataset","Last Name");
    protected String PostalCode = GetJsonData("Dataset","Postal Code");
    protected String Confirmation_Message = GetJsonData("Dataset","Confirmation Message");

    @Test
    public void E2E_Checkout(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(Inventory_Title));

        Inventory_Page inventoryPage = new Inventory_Page(GetDriver());
        inventoryPage.AddProductToCart(Product1);
        inventoryPage.AddProductToCart(Product2);

        inventoryPage.OpenCart();
        Cart_Page cartPage = new Cart_Page(GetDriver());
        Assert.assertTrue(cartPage.isProductDisplayed(Product1));
        Assert.assertTrue(cartPage.isProductDisplayed(Product2));

        cartPage.ClickOnCheckout();
        Checkout_Page checkoutPage = new Checkout_Page(GetDriver());
        checkoutPage
                .EnterCustomerInformation(FirstName,LastName,PostalCode)
                .ClickOnContinue();
        Assert.assertTrue(checkoutPage.isProductDisplayed(Product1));
        Assert.assertTrue(checkoutPage.isProductDisplayed(Product2));
        Assert.assertEquals(checkoutPage.GetDisplayedSubtotal(),checkoutPage.GetExpectedSubtotal(),0.01);
        Assert.assertEquals(checkoutPage.GetDisplayedTax(),checkoutPage.GetExpectedTax(),0.01);
        Assert.assertEquals(checkoutPage.GetDisplayedTotal(),checkoutPage.GetExpectedTotal(),0.01);

        checkoutPage.ClickOnFinish();
        Assert.assertEquals(checkoutPage.GetOrderConfirmation(),Confirmation_Message);
    }
}