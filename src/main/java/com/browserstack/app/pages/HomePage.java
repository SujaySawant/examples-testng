package com.browserstack.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HomePage extends BasePage {

    private final By signInLink = By.id("signin");
    private final By ordersLink = By.cssSelector("a#orders");
    private final By username = By.className("username");
    private final By favourites = By.id("favourites");
    private final By offers = By.id("offers");
    private final By orderBy = By.cssSelector(".sort select");
    private final By loading = By.className("spinner lds-ring");

    public LoginPage navigateToSignIn() {
        wait.until(elementToBeClickable(signInLink)).click();
        return new LoginPage();
    }

    public HomePage addProductToCart(String productName) {
        By product = By.xpath("//p[text() = '" + productName + "']/../div[@class = 'shelf-item__buy-btn']");
        wait.until(elementToBeClickable(product)).click();
        getBag().close();
        return this;
    }

    public OrdersPage navigateToOrders() {
        wait.until(elementToBeClickable(ordersLink)).click();
        return new OrdersPage();
    }

    public Bag getBag() {
        return new Bag();
    }

    public String getUsername() {
        return wait.until(presenceOfElementLocated(username)).getText();
    }

    public FavouritesPage navigateToFavouritesPage() {
        wait.until(elementToBeClickable(favourites)).click();
        return new FavouritesPage();
    }

    public OffersPage navigateToOffersPage() {
        wait.until(elementToBeClickable(offers)).click();
        return new OffersPage();
    }

    public HomePage selectOrderBy(String value) {
        Select sortSelect = new Select(driver.findElement(orderBy));
        sortSelect.selectByValue(value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<Integer> getAllProductValues() {
        wait.until(invisibilityOfElementLocated(loading));
        return driver.findElements(By.cssSelector(".val > b"))
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt)
                .collect(toList());
    }

    public HomePage clickProductVendor(String vendor) {
        driver.findElement(By.cssSelector("input[value='" + vendor + "'] + span")).click();
        return this;
    }

    public HomePage waitForProductsFound(String numberOfProducts) {
        wait.until(textToBePresentInElementLocated(By.cssSelector(".products-found"), numberOfProducts + " Product(s) found."));
        return this;
    }

    public List<String> getAllProductNames() {
        return driver.findElements(By.cssSelector(".shelf-item__title"))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(toList());
    }

    public List<WebElement> getAllEmptyImageSrcElements() {
        return wait.until(presenceOfAllElementsLocatedBy(By.cssSelector(".shelf-item__thumb img")))
                .stream()
                .filter(image -> !image.getAttribute("src").equals(""))
                .collect(toList());
    }

    public HomePage clickFavouriteItem(String itemNumber) {
        wait.until(elementToBeClickable(By.cssSelector("div[id='" + itemNumber + "'] > div > button"))).click();
        return this;
    }
}
