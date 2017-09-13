package com.maxpay.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.maxpay.utils.Selenium.waitForElementVisible;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by Olexander.Korniev on 04.09.2017.
 */

public class LoginPage {
    private final WebDriver webdriver;
    public final static String USER_NAME = "test_user@test.com";
    public final static String USER_PASSWORD = "Qwerty123";

    public LoginPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public MainPage loginAndProceed(String userName, String userPass) {
        userNameField.sendKeys(userName);
        userPasswordField.sendKeys(userPass);
        loginButton.click();
        new WebDriverWait(webdriver, 4).until(visibilityOf(clientsButton));
        return PageFactory.initElements(webdriver, MainPage.class);
    }

    public void login(String userName, String userPass) {
        waitForElementVisible(userNameField, webdriver);
        userNameField.sendKeys(userName);
        userPasswordField.sendKeys(userPass);
        loginButton.click();
    }

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (id = "login-email")
    public WebElement userNameField;

    @FindBy (id = "login-email-error")
    public WebElement userNameErrorField;

    @FindBy (id = "login-password")
    public WebElement userPasswordField;

    @FindBy (id = "login-password-error")
    public WebElement userPasswordErrorField;

    //MainPageElement
    @FindBy(xpath = "//a[@id='count-customer-procesed']/div")
    public WebElement clientsButton;
}
