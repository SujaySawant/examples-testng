package com.browserstack.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class OffersPage extends BasePage {

    public List<WebElement> getAllOffers() {
        By offers = By.cssSelector(".offer");
        return wait.until(presenceOfAllElementsLocatedBy(offers));
    }
}
