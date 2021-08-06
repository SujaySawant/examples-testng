package com.browserstack.test.utils;

import com.browserstack.test.suites.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class BrowserstackTestStatusListener extends TestListenerAdapter {

    private void markTestStatus(String status, String reason) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) TestBase.getDriver();
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}");
        } catch (Exception e) {
            System.out.print("Error executing javascript" + e.getLocalizedMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        markTestStatus("passed", "");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String message = result.getThrowable().getClass().getSimpleName();
        markTestStatus("failed", message);
    }

}
