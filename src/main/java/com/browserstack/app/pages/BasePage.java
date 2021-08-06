package com.browserstack.app.pages;

import com.browserstack.app.utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = TestBase.getDriverThread();
        this.wait = new WebDriverWait(driver, 10);
    }

}
