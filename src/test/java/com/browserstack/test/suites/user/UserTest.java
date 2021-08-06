package com.browserstack.test.suites.user;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.utils.TestBase;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserTest extends TestBase {

    @Test(description = "Logged in user with empty images")
    public void loginImagesNotLoading() {
        List<WebElement> imageSrc = new HomePage()
                .navigateToSignIn()
                .loginWith("image_not_loading_user", "testingisfun99")
                .getAllEmptyImageSrcElements();
        Assert.assertTrue(!imageSrc.isEmpty(), "Images are not displayed");
    }

    @Test(description = "Login and check existing orders")
    public void loginAndCheckExistingOrders() {
        int numberOfOrders = new HomePage()
                .navigateToSignIn()
                .loginWith("existing_orders_user", "testingisfun99")
                .navigateToOrders()
                .getNumberOfOrders();
        Assertions.assertThat(numberOfOrders).isGreaterThanOrEqualTo(5);
    }

    @Test(description = "Logged in user should be able to add favourite")
    public void addToFavourites() {
        List<String> items = new HomePage()
                .navigateToSignIn()
                .loginWith("demouser", "testingisfun99")
                .clickFavouriteItem("1")
                .navigateToFavouritesPage()
                .getAllProductNames();
        Assert.assertTrue(items.contains("iPhone 12"), "iPhone 12 is not present");
    }

}
