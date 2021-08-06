package com.browserstack.app.pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CheckoutPage extends BasePage {

    private final By firstnameInput = By.id("firstNameInput");
    private final By lastnameInput = By.id("lastNameInput");
    private final By addressInput = By.id("addressLine1Input");
    private final By stateInput = By.id("provinceInput");
    private final By postcodeInput = By.id("postCodeInput");
    private final By checkoutButton = By.id("checkout-shipping-continue");

    public ConfirmationPage enterShippingDetails(String firstname, String lastname, String address, String state, String postcode) {
        wait.until(elementToBeClickable(firstnameInput)).sendKeys(firstname);
        driver.findElement(lastnameInput).sendKeys(lastname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(postcodeInput).sendKeys(postcode);
        driver.findElement(checkoutButton).click();
        return new ConfirmationPage();
    }

}
