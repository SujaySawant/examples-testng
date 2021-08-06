package com.browserstack.test.suites.login;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test(description = "Login successful")
    public void loginSuccess() {
        HomePage homePage = new HomePage()
                .navigateToSignIn()
                .loginWith("fav_user", "testingisfun99");
        Assert.assertEquals(homePage.getUsername(), "fav_user", "Incorrect username");
    }

}
