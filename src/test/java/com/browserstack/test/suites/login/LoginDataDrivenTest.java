package com.browserstack.test.suites.login;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.pages.LoginPage;
import com.browserstack.app.utils.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDataDrivenTest extends TestBase {

    @DataProvider(name = "login_error_messages")
    public Object[][] data() {
        return new Object[][]{
                {"locked_user", "testingisfun99", "Your account has been locked."},
                {"fav_user", "wrongpass", "Invalid Password"},
                {"helloworld", "testingisfun99", "Invalid Username"}
        };
    }

    @Test(description = "Unsuccessful login with error message", dataProvider = "login_error_messages")
    public void validateErrors(String username, String password, String error) {
        LoginPage loginPage = new HomePage()
                .navigateToSignIn()
                .failedLoginWith(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), error, "Incorrect error message");
    }

}
