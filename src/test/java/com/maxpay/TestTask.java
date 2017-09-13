package com.maxpay;

import com.maxpay.pageobject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.maxpay.Hostname.maxpayLoginPage;
import static com.maxpay.pageobject.LoginPage.USER_NAME;
import static com.maxpay.pageobject.LoginPage.USER_PASSWORD;
import static com.maxpay.utils.Selenium.*;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Olexander.Korniev on 04.09.2017.
 */

public class TestTask {
    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeMethod
    public void before(){
        System.setProperty(ConfigProperties.getProperty("chrome"), ConfigProperties.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositive() throws InterruptedException {
        driver.get(maxpayLoginPage());
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(USER_NAME, USER_PASSWORD);
        waitForElementVisible(loginPage.clientsButton, driver);
        assertTrue(driver.getTitle().equals("Dashboard — Merchant portal"));
    }

    @DataProvider
    public Object[] [] userEmails () {
        return new Object[][]{
                //User name, user password
                {"", ""},
                {USER_NAME, ""},
                /*{"", USER_PASSWORD},
                {USER_NAME, "WRONG_PASS"},
                {"WRONG_LOGIN", USER_PASSWORD},
                {USER_PASSWORD, USER_NAME},
                {"<script>alert(345)</script", USER_PASSWORD},
                {"SELECT * FROM blog WHERE code LIKE ‘a%’;", USER_PASSWORD},
                {"<script>alert(“Hello, world!”)</alert>", USER_PASSWORD},
                {"<form action=”http://live.hh.ru”><input type=”submit”></form", USER_PASSWORD},
                {"“♣☺♂” , “”‘~!@#$%^&*()?>,./\\<][ *//*<!–“”, “${code}”;–>", USER_NAME},
                {"                                                                                                                                     ", USER_PASSWORD},
                {"SELECT * FROM blog WHERE code LIKE ‘a%’;", USER_PASSWORD},
                {"   " + USER_NAME, USER_PASSWORD},
                {USER_NAME + "   ", "QweRtY123"},
                {"tesT_UseR@teSt.Com", USER_PASSWORD},
                {"test_user", USER_PASSWORD},
                {"123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"}
*/        };
    }

    @Test (dataProvider = "userEmails")
    public void loginNegative(String userName, String userPass) throws InterruptedException {
        driver.get(maxpayLoginPage());
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(userName, userPass);
        waitUntilElementIsDisplayed(loginPage.userNameErrorField);
        assertEquals(loginPage.userNameErrorField.getText().toString(), "Please provide a password");
    }

    @AfterMethod
    public void after(){
        driver.close();
    }
}
