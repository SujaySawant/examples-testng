package com.browserstack.app.pages;

import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class OrdersPage extends BasePage {

    public int getItemsFromOrder() {
        return wait.until(presenceOfAllElementsLocatedBy(By.cssSelector(".shipment .a-fixed-right-grid > div"))).size();
    }

    public int getNumberOfOrders() {
       return wait.until(presenceOfAllElementsLocatedBy(By.cssSelector(".order"))).size();
    }
}
