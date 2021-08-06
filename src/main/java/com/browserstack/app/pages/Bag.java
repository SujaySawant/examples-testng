package com.browserstack.app.pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Bag extends BasePage {

    private final By closeButton = By.className("float-cart__close-btn");
    private final By bagButton = By.cssSelector(".bag");
    private final By bagClosed = By.cssSelector(".bag--float-cart-closed");
    private final By bagOpen = By.cssSelector(".float-cart--open");
    private final By buyButton = By.className("buy-btn");
    private final By quantityContainer = By.cssSelector(".bag__quantity");

    public CheckoutPage proceedToCheckout() {
        wait.until(visibilityOfElementLocated(bagClosed));
        wait.until(elementToBeClickable(bagButton)).click();
        wait.until(visibilityOfElementLocated(bagOpen));
        wait.until(elementToBeClickable(buyButton)).click();
        return new CheckoutPage();
    }

    public void close() {
        wait.until(elementToBeClickable(closeButton)).click();
    }

    public HomePage waitForItemsInBag(int i) {
        wait.until(textToBePresentInElementLocated(quantityContainer, Integer.toString(i)));
        return new HomePage();
    }
}
