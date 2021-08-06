package com.browserstack.test.suites.product;

import com.browserstack.app.pages.HomePage;
import com.browserstack.app.utils.TestBase;
import com.browserstack.app.utils.CsvUtil;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilterTest extends TestBase {

    @Test(description = "Lowest to Highest filter applied")
    public void filterLowestToHighestTest() {
        List<Integer> values = new HomePage()
                .selectOrderBy("lowestprice")
                .getAllProductValues();
        Assertions.assertThat(values).isSorted();
    }

    @Test(description = "Vendor filter applied")
    public void filterVendorTest() throws Exception {
        HomePage homePage = new HomePage();
        homePage.clickProductVendor("Apple").waitForProductsFound("9");

        List<String> values = homePage.clickProductVendor("Samsung")
                .waitForProductsFound("16")
                .getAllProductNames();
        List<String> expectedValues = CsvUtil.readSpecificColumn("src/test/resources/data/products.csv", 2);
        Assert.assertEquals(values, expectedValues, "Incorrect product names");
    }
}
