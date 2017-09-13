package com.maxpay.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Olexander.Korniev on 04.09.2017.
 */

public class Selenium {

    public static final byte WAIT_TIME = 10;

    public static void waitForElementVisible(WebElement webElement, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitUntilElementIsDisplayed(WebElement webElement) throws InterruptedException {
        int i = 0;
        Thread.sleep(200);
        while (!webElement.isDisplayed()) {
            Thread.sleep(200);
            i++;
            if (i == 5 * WAIT_TIME){
                break;
            }
        }
    }
}
