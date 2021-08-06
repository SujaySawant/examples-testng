package com.browserstack.test.utils;

import com.browserstack.test.suites.TestBase;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

    @Attachment(value = "Failure screenshot", type = "image/png")
    public byte[] attachFailedScreenshot() {
        return ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            attachFailedScreenshot();
        }
    }
}