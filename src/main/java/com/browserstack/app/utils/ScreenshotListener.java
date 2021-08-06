package com.browserstack.app.utils;

import com.browserstack.app.utils.TestBase;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

    @Attachment(value = "Failure screenshot", type = "image/png")
    public byte[] attachFailedScreenshot() {
        return ((TakesScreenshot) TestBase.getDriverThread()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            attachFailedScreenshot();
        }
    }
}