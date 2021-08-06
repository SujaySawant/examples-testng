package com.browserstack.test.suites.login;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.pages.LoginPage;
import com.browserstack.app.utils.TestBase;
import com.browserstack.app.utils.CsvUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class LoginDataDrivenReadFromCSVTest extends TestBase {

    @DataProvider(name = "login_error_messages")
    public Iterator<String[]> provider() throws Exception {
        return CsvUtil.readAll("src/test/resources/data/users.csv", false);
    }

    @Test(description = "Unsuccessful login with error message from csv", dataProvider = "login_error_messages")
    public void validateErrorsFromCsv(String username, String password, String error) {
        LoginPage loginPage = new HomePage()
                .navigateToSignIn()
                .failedLoginWith(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), error, "Incorrect error message");
    }

}
