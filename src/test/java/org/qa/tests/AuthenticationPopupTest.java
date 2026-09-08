package org.qa.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthenticationPopupTest {

    // define a web driver
    WebDriver driver;

    public void login() {
        driver = new ChromeDriver();
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        // use the explicit wait command
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Alert class to verify the alert
        // Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        // Once verified, provide the credentials
        // alert.accept();
    }

    public static void main(String[] args) {
        AuthenticationPopupTest test = new AuthenticationPopupTest();
        test.login();
    }
}
