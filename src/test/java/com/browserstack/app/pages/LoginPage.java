package com.browserstack.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage extends BasePage {

    private final By usernameInput = By.cssSelector("#username input");
    private final By passwordInput = By.cssSelector("#password input");
    private final By logInButton = By.id("login-btn");
    private final By errorMessage = By.className("api-error");

    public HomePage loginWith(String username, String password) {
        wait.until(elementToBeClickable(usernameInput)).sendKeys(username + Keys.ENTER);
        driver.findElement(passwordInput).sendKeys(password + Keys.ENTER);
        driver.findElement(logInButton).click();
        return new HomePage();
    }

    public LoginPage failedLoginWith(String username, String password) {
        wait.until(elementToBeClickable(usernameInput)).sendKeys(username + Keys.ENTER);
        driver.findElement(passwordInput).sendKeys(password + Keys.ENTER);
        driver.findElement(logInButton).click();
        return this;
    }

    public String getErrorMessage() {
        return wait.until(presenceOfElementLocated(errorMessage)).getText();
    }

}
