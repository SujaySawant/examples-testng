package com.browserstack.app.pages;

import com.browserstack.test.suites.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = TestBase.getDriver();
        this.wait = new WebDriverWait(driver, 10);
    }

}
