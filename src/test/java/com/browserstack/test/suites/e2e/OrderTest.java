package com.browserstack.test.suites.e2e;

import com.browserstack.app.pages.ConfirmationPage;
import com.browserstack.app.pages.HomePage;
import com.browserstack.app.pages.OrdersPage;
import com.browserstack.app.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends TestBase {

    @Test(description = "Login with user and place order")
    public void placeOrder() {
        ConfirmationPage page = new HomePage()
                .navigateToSignIn()
                .loginWith("fav_user", "testingisfun99")
                .addProductToCart("iPhone 11")
                .addProductToCart("iPhone XS Max")
                .addProductToCart("Galaxy S20")
                .getBag().waitForItemsInBag(3)
                .getBag().proceedToCheckout()
                .enterShippingDetails("firstname", "lastname", "address", "state", "12345");
        Assert.assertTrue(page.isConfirmationDisplayed(), "Confirmation page is not displayed");

        OrdersPage ordersPage = page.continueShopping().navigateToOrders();

        Assert.assertEquals(ordersPage.getItemsFromOrder(), 3, "Incorrect number of Items");
    }
}