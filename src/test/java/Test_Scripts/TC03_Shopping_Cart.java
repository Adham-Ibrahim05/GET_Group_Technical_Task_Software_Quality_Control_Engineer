package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Cart_Page;
import Pages.Login_Page;
import Pages.Inventory_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC03_Shopping_Cart extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Inventory_Title = GetJsonData("Dataset","Inventory Title");
    protected String Product1 = GetJsonData("Dataset","Product 1");
    protected String Product2 = GetJsonData("Dataset","Product 2");
    protected int CartCount_Add = Integer.parseInt(GetJsonData("Dataset","Cart Count - 2 Products"));
    protected int CartCount_Remove = Integer.parseInt(GetJsonData("Dataset","Cart Count After Remove"));

    @Test
    public void Add_RemoveProductFromCart(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(Inventory_Title));

        Inventory_Page inventoryPage = new Inventory_Page(GetDriver());
        inventoryPage.AddProductToCart(Product1);
        inventoryPage.AddProductToCart(Product2);
        Assert.assertEquals(inventoryPage.GetCartBadgeCount(),CartCount_Add);

        inventoryPage.OpenCart();
        Cart_Page cartPage = new Cart_Page(GetDriver());
        Assert.assertTrue(cartPage.isProductDisplayed(Product1));
        Assert.assertTrue(cartPage.isProductDisplayed(Product2));
        Assert.assertEquals(cartPage.GetCartItemCount(),CartCount_Add);

        cartPage.RemoveProductFromCart(Product1);
        Assert.assertFalse(cartPage.isProductDisplayed(Product1));
        Assert.assertTrue(cartPage.isProductDisplayed(Product2));
        Assert.assertEquals(cartPage.GetCartBadgeCount(),CartCount_Remove);
        Assert.assertEquals(cartPage.GetCartItemCount(),CartCount_Remove);

        List<String> finalCartContents = cartPage.GetCartProductNames();
        Assert.assertEquals(finalCartContents, List.of(Product2));
    }

}