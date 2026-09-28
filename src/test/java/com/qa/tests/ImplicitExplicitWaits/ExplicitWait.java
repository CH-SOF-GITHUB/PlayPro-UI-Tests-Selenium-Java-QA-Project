package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExplicitWait {
    WebDriver driver;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    // @BeforeMethod: The annotation method will be run before each test method.
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }


    @Test(enabled = false)
    public void test2() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until((d) -> {
            return d.findElement(By.id("success"));
        });
    }

    @Test(enabled = false)
    public void test3() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Hello World!']"))).isDisplayed());
    }

    @Test(enabled = false)
    public void test4() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.ignoring(WebDriverException.class);
        wait.withMessage("Tried to found element with ID finish but the element wasn't clickable on the page after 5 seconds");
        try {
            WebElement x = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[normalize-space()='Hello World!']")));
        } catch (WebDriverException ex) {
            driver.findElement(new By.ByTagName("button")).click();
            WebElement x = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Hello World!']")));
        }
    }

}
