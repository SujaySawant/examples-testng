package com.browserstack.test.suites.login;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.pages.LoginPage;
import com.browserstack.test.suites.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFailTest extends TestBase {

    @Test(description = "Login failure with wrong password")
    public void loginFail() {
        LoginPage loginPage = new HomePage()
                .navigateToSignIn()
                .failedLoginWith("fav_user", "wrongpass");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid Password", "Incorrect error message");
    }

    @Test(description = "Login failure with locked user")
    public void loginLockedUser() {
        LoginPage loginPage = new HomePage()
                .navigateToSignIn()
                .failedLoginWith("locked_user", "testingisfun99");
        Assert.assertEquals(loginPage.getErrorMessage(), "Your account has been locked.", "Incorrect error message");
    }
}
