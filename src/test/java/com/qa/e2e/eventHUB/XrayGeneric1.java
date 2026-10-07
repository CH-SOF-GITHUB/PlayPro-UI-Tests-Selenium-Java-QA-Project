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

public class XrayGeneric1 extends BaseClass {

    // create a private WebDriverWait variable and ActionDriver variable
    private WebDriverWait wait;
    private ActionDriver actionDriver;


    @Test
    public void VerifyLoginWithInvalidEmail() {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        this.actionDriver = BaseClass.getActionDriver();
        openEventHUBLoginPage();
        signIn("bchaker289@yahoo.com", "Q5n@j!i!QnZQmYm");
        checkPwdOrEmailErrorMessage("Invalid email or password");
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

    public void checkPwdOrEmailErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'text-sm font-medium flex-1 leading-snug')]"))).getText();
        boolean isErrorMessageCorrect = actualErrorMessage.equals(expectedErrorMessage);
        Assert.assertTrue(isErrorMessageCorrect);
    }
}
