package Pages;

import Utilities.GeneralUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Cart_Page {
    private final WebDriver driver;
    private final By CartItems = By.cssSelector(".cart_item");
    private final By CartItemNames = By.cssSelector(".inventory_item_name");
    private final By CartBadge = By.cssSelector(".shopping_cart_badge");
    private final By ContinueShoppingButton = By.id("continue-shopping");
    private final By CheckoutButton = By.id("checkout");

    public Cart_Page(WebDriver driver) {
        this.driver = driver;
    }

    public void RemoveProductFromCart(String productName)
    {
        By RemoveButton =  By.xpath( "//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + productName + "']]//button");
        GeneralUtility.Click_OnElement(driver, RemoveButton);
    }

    public List<String> GetCartProductNames() {

        List<String> productNames = new ArrayList<>();

        for (WebElement product : driver.findElements(CartItemNames)) {
            productNames.add(product.getText());
        }

        return productNames;
    }

    public int GetCartItemCount() {

        return driver.findElements(CartItems).size();
    }

    public int GetCartBadgeCount() {
        return Integer.parseInt(GeneralUtility.GetText(driver, CartBadge));
    }

    public void ContinueShopping() {

        GeneralUtility.Click_OnElement(driver,ContinueShoppingButton);
    }

    public void ClickOnCheckout()
    {
        GeneralUtility.Click_OnElement(driver,CheckoutButton);
    }

    public boolean isProductDisplayed(String productName) {
        By productNameLocator = By.xpath("//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + productName + "']]//div[@class='inventory_item_name']");
        return !driver.findElements(productNameLocator).isEmpty();
    }

}