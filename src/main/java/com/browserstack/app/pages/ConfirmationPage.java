package com.browserstack.app.pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class ConfirmationPage extends BasePage {

    private final By continueShoppingButton = By.cssSelector(".continueButtonContainer button");
    private final By confirmationMessage = By.id("confirmation-message");

    public boolean isConfirmationDisplayed() {
        return wait.until(textToBePresentInElementLocated(confirmationMessage, "Your Order has been successfully placed."));
    }

    public HomePage continueShopping() {
        driver.findElement(continueShoppingButton).click();
        return new HomePage();
    }
}
