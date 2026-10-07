package com.qa.e2e.eventHUB;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class XrayGeneric extends BaseClass {
    private WebDriverWait wait;
    private ActionDriver actionDriver;
    // @BeforeMethod: The annotation method will be run before each test method.
    // @AfterMethod: The annotation method will be run after each test method.
    // @AfterMethod
    // public void end() {
    //    driver.close();
    // }

    @Test
    public void VerifyLoginWithInvalidPwd() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        this.actionDriver = BaseClass.getActionDriver();
        openEventHUBLoginPage();
        signIn("bchaker28@yahoo.com", "P@ssword2026!");
        checkPwdErrorMessage("Invalid email or password");
    }

    public void openEventHUBLoginPage() {
        actionDriver.openPage("https://eventhub.rahulshettyacademy.com/login");
    }

    public void signIn(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        WebElement SignInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Sign In'])[1]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", SignInButton);
    }

    public void checkPwdErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-sm font-medium flex-1 leading-snug')]"))).getText();
        boolean isErrorMessageCorrect = actualErrorMessage.equals(expectedErrorMessage);
        Assert.assertTrue(isErrorMessageCorrect);
    }
}
