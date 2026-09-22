package Listeners;

import Utilities.GeneralUtility;
import Utilities.LogsUtility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static Driver_Factory.Driver_Factory.GetDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        LogsUtility.LoggerInfo("Test Case " + context.getStartDate());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        //Utility.takeFullScreenshot(getDriver(), new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart());
        switch (testResult.getStatus()) {
            case ITestResult.FAILURE:
                LogsUtility.LoggerInfo("Test Case " + testResult.getName() + " failed");
                GeneralUtility.TakingScreenShot(GetDriver(), testResult.getName()); //validLoginTC-2024-03-03-8-17pm
                break;
            case ITestResult.SUCCESS:
                LogsUtility.LoggerInfo("Test Case " + testResult.getName() + " passed");
                break;
            case ITestResult.SKIP:
                LogsUtility.LoggerInfo("Test Case " + testResult.getName() + " skipped");
                break;
        }

        try {
            File logFile = GeneralUtility.GetLatestFile(LogsUtility.Logs_Path);

            if (logFile != null && logFile.exists()) {
                Allure.addAttachment(
                        "logs.log",
                        Files.readString(Path.of(logFile.getPath()))
                );
            } else {
                LogsUtility.LoggerError("Log file was not found in: " + LogsUtility.Logs_Path);
            }
        } catch (IOException e) {
            LogsUtility.LoggerError(e.getMessage());
        }
    }
}