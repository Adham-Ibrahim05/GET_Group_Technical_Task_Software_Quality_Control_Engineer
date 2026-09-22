package Pages;

import Utilities.GeneralUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Inventory_Page {
    private final WebDriver driver;

    private final By CartIcon = By.cssSelector(".shopping_cart_link");
    private final By CartBadge = By.cssSelector(".shopping_cart_badge");
    private final By ProductNames = By.cssSelector(".inventory_item_name");
    private final By ProductPrices = By.cssSelector(".inventory_item_price");
    private final By SortDropdown = By.className("product_sort_container");
    private final By Sort_A_Z = By.xpath("//option[@value='az']");
    private final By Sort_Low_High = By.xpath("//option[@value='lohi']");
    private final By ProductDetailName = By.className("inventory_details_name");
    private final By ProductDetailPrice = By.className("inventory_details_price");
    private final By ProductDetailDescription = By.className("inventory_details_desc");
    private final By BackToInventory = By.id("back-to-products");


    public Inventory_Page(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> GetProductNames() {

        List<String> productNames = new ArrayList<>();

        List<WebElement> products =
                driver.findElements(ProductNames);

        for (WebElement product : products) {
            productNames.add(product.getText());
        }

        return productNames;
    }

    public List<Double> GetProductPrices() {

        List<Double> productPrices = new ArrayList<>();

        List<WebElement> prices =
                driver.findElements(ProductPrices);

        for (WebElement price : prices) {
            productPrices.add(
                    Double.parseDouble(
                            price.getText().split("\\$")[1]
                    )
            );
        }

        return productPrices;
    }

    public void SortByNameAtoZ() {
        GeneralUtility.Click_OnElement(driver,SortDropdown);
        GeneralUtility.Click_OnElement(driver,Sort_A_Z);
    }

    public void SortByPriceLowToHigh() {
        GeneralUtility.Click_OnElement(driver,SortDropdown);
        GeneralUtility.Click_OnElement(driver,Sort_Low_High);
    }

    public void OpenProduct(String productName) {
        By product = By.xpath("//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']");
        GeneralUtility.Click_OnElement(driver, product);
    }

    public String GetProductDetailName() {
        return GeneralUtility.GetText(driver, ProductDetailName);
    }

    public String GetProductDetailPrice() {
        return GeneralUtility.GetText(driver, ProductDetailPrice);
    }

    public String GetProductDetailDescription() {
        return GeneralUtility.GetText(driver, ProductDetailDescription);
    }

    public void BackToInventory() {
        GeneralUtility.Click_OnElement(driver, BackToInventory);
    }

    public void AddProductToCart(String productName)
    {
        By AddButton = By.xpath("//div[contains(@class,'inventory_item')]" + "[.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]//button[contains(@data-test,'add-to-cart')]");
        GeneralUtility.Click_OnElement(driver, AddButton);
    }

    public int GetCartBadgeCount() {
        return Integer.parseInt(GeneralUtility.GetText(driver, CartBadge));
    }

    public void OpenCart()
    {
        GeneralUtility.Click_OnElement(driver, CartIcon);
    }

}