package Test_Scripts;


import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Pages.Inventory_Page;
import Pages.Login_Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Driver_Factory.Driver_Factory.GetDriver;
import static Utilities.DataUtility.GetJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class TC02_Product_Inventory_Sorting extends BaseTest{

    protected String ValidUsername = GetJsonData("Dataset","Valid Username");
    protected String Password = GetJsonData("Dataset","Password");
    protected String Inventory_Title = GetJsonData("Dataset","Inventory Title");
    protected String Product1 = GetJsonData("Dataset","Product 1");

    @Test
    public void Product_Inventory_Sorting(){
        Login_Page loginPage = new Login_Page(GetDriver());
        loginPage
                .EnterUsername(ValidUsername)
                .EnterPassword(Password)
                .ClickOnLogin();
        Assert.assertTrue(loginPage.isInventoryPageDisplayed(Inventory_Title));

        Inventory_Page inventoryPage = new Inventory_Page(GetDriver());

        List<String> productNames = inventoryPage.GetProductNames();
        List<Double> productPrices = inventoryPage.GetProductPrices();
        Assert.assertFalse(productNames.isEmpty());
        Assert.assertFalse(productPrices.isEmpty());

        inventoryPage.SortByPriceLowToHigh();
        List<Double> actualPrices = inventoryPage.GetProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices);

        inventoryPage.SortByNameAtoZ();
        List<String> actualNames = inventoryPage.GetProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);
        Assert.assertEquals(actualNames, expectedNames);

        inventoryPage.OpenProduct(Product1);
        Assert.assertEquals(inventoryPage.GetProductDetailName(), Product1);
        Assert.assertFalse(inventoryPage.GetProductDetailPrice().isEmpty());
        Assert.assertFalse(inventoryPage.GetProductDetailDescription().isEmpty());

        inventoryPage.BackToInventory();
        Assert.assertFalse(inventoryPage.GetProductNames().isEmpty());
    }
}