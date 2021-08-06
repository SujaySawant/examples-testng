package com.browserstack.test.suites.login;

import com.browserstack.app.pages.FavouritesPage;
import com.browserstack.app.pages.HomePage;
import com.browserstack.app.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRequestedTest extends TestBase {

    @Test(description = "Login and navigate to favourites page")
    public void navigateFavoritesLoginRequested() {
        FavouritesPage favouritesPage = new HomePage().navigateToFavouritesPage();
        Assert.assertTrue(favouritesPage.urlContainsFavourites(), "URL does not contain favourites");
    }
}
