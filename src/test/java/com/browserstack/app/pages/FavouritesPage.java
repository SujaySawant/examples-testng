package com.browserstack.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class FavouritesPage extends BasePage {

    public List<String> getAllProductNames() {
        return driver.findElements(By.cssSelector(".shelf-item__title"))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(toList());
    }

    public boolean urlContainsFavourites() {
        return wait.until(urlContains("favourites"));
    }

}
