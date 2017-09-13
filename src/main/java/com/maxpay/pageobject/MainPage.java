package com.maxpay.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Администратор on 04.09.2017.
 */
public class MainPage {

    @FindBy(xpath = "//div[@id='customers-filter']/div[3]/button")
    public WebElement createReportButton;
}
