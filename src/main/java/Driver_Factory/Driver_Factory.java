package Driver_Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


/* This for parallel execution and thread local */
public class Driver_Factory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void SetupDriver(String browser) {
        String actualBrowser = browser.toLowerCase();

        switch (actualBrowser) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(edgeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
                firefoxDriver.manage().window().maximize();
                driverThreadLocal.set(firefoxDriver);
                break;

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                driverThreadLocal.get().manage().window().maximize();
        }
    }

    public static WebDriver GetDriver() {
        return driverThreadLocal.get();
    }

    public static void QuitDriver() {
        GetDriver().quit();
        driverThreadLocal.remove();
    }
}
