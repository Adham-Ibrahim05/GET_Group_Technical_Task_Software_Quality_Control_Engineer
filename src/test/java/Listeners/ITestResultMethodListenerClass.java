package Listeners;

import Utilities.LogsUtility;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultMethodListenerClass implements ITestListener {

    public void onTestStart(ITestResult result) {
        LogsUtility.LoggerInfo("Test Case " + result.getName() + "Started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtility.LoggerInfo("Test Case " + result.getName() + "Passed");
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtility.LoggerInfo("Test Case " + result.getName() + "Skipped");
    }

}
