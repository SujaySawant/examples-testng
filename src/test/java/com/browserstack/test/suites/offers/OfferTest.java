package com.browserstack.test.suites.offers;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.utils.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OfferTest extends TestBase {
    private static final String LOCATION_SCRIPT_FORMAT = "navigator.geolocation.getCurrentPosition = function(success){\n" +
            "    var position = { \"coords\":{\"latitude\":\"%s\",\"longitude\":\"%s\"}};\n" +
            "    success(position);\n" +
            "}";
    private static final String OFFER_LATITUDE = "1";
    private static final String OFFER_LONGITUDE = "103";

    @Test(description = "Check offers in Singapore")
    public void checkOffersInSingapore() {
        String locationScript = String.format(LOCATION_SCRIPT_FORMAT, OFFER_LATITUDE, OFFER_LONGITUDE);
        ((JavascriptExecutor) getDriverThread()).executeScript(locationScript);

        int offerSize = new HomePage()
                .navigateToSignIn()
                .loginWith("fav_user", "testingisfun99")
                .navigateToOffersPage()
                .getAllOffers()
                .size();
        Assert.assertEquals(offerSize, 3, "Incorrect offer size");
    }

}
