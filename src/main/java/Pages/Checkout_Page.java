package Pages;

import Utilities.GeneralUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Checkout_Page {
    private final WebDriver driver;
    private final By FirstName = By.id("first-name");
    private final By LastName = By.id("last-name");
    private final By PostalCode = By.id("postal-code");
    private final By ContinueButton = By.id("continue");
    private final By ItemTotal = By.className("summary_subtotal_label");
    private final By Tax = By.className("summary_tax_label");
    private final By Total = By.className("summary_total_label");
    private final By FinishButton = By.id("finish");
    private final By OrderConfirmation = By.className("complete-header");
    private final By ProductNames = By.cssSelector(".inventory_item_name");
    private final By ProductPrices = By.cssSelector(".inventory_item_price");


    public Checkout_Page(WebDriver driver) {
        this.driver = driver;
    }

    public Checkout_Page EnterCustomerInformation(String firstName, String lastName, String postalCode) {
        GeneralUtility.SendData(driver,FirstName,firstName);
        GeneralUtility.SendData(driver,LastName,lastName);
        GeneralUtility.SendData(driver,PostalCode,postalCode);
        return this;
    }

    public void ClickOnContinue()
    {
        GeneralUtility.Click_OnElement(driver,ContinueButton);
    }

    public double GetProductsSubtotal() {
        double subtotal = 0;

        List<WebElement> prices =
                driver.findElements(ProductPrices);

        for (WebElement price : prices) {

            String priceText = price.getText();

            subtotal += Double.parseDouble(
                    priceText.split("\\$")[1]
            );
        }

        return subtotal;
    }

    public double GetDisplayedSubtotal() {

        String subtotal =
                GeneralUtility.GetText(driver, ItemTotal);

        return Double.parseDouble(
                subtotal.split("\\$")[1]
        );
    }

    public double GetDisplayedTax() {

        String tax =
                GeneralUtility.GetText(driver, Tax);

        return Double.parseDouble(
                tax.split("\\$")[1]
        );
    }

    public double GetDisplayedTotal() {

        String total =
                GeneralUtility.GetText(driver, Total);

        return Double.parseDouble(
                total.split("\\$")[1]
        );
    }

    public double GetExpectedSubtotal() {
        return GetProductsSubtotal();
    }

    public double GetExpectedTax() {
        return GetProductsSubtotal() * 0.08;
    }

    public double GetExpectedTotal() {

        double subtotal = GetProductsSubtotal();
        double tax = subtotal * 0.08;

        return subtotal + tax;
    }

    public void ClickOnFinish() {
        GeneralUtility.Click_OnElement(driver, FinishButton);
    }

    public String GetOrderConfirmation() {
        return GeneralUtility.GetText(driver, OrderConfirmation);
    }

    public boolean isProductDisplayed(String productName) {
        By productNameLocator = By.xpath("//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='" + productName + "']]//div[@class='inventory_item_name']");
        return !driver.findElements(productNameLocator).isEmpty();
    }

}