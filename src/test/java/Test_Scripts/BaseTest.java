package Test_Scripts;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultMethodListenerClass;
import Utilities.LogsUtility;
import org.testng.annotations.*;

import static Driver_Factory.Driver_Factory.*;
import static Utilities.DataUtility.GetPropertiesDataFromFile;

@Listeners({IInvokedMethodListenerClass.class, ITestResultMethodListenerClass.class})
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void Setup(@Optional String browser)
    {

        // If browser is not provided in XML, fallback to environment.properties
        if(browser == null || browser.isEmpty()){
            browser = GetPropertiesDataFromFile("environment","Browser");
        }

        // Initialize the browser
        SetupDriver(browser);
        LogsUtility.LoggerInfo("Browser is Opened");

        //Start use the driver
        GetDriver().get(GetPropertiesDataFromFile("environment","Login_URL"));
        LogsUtility.LoggerInfo("Page is redirecting to the URL");

    }

    @AfterMethod
    public void Quit()
    {
        QuitDriver();
    }
}
