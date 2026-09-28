package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplicitWait {
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
    public void test1() throws InterruptedException {
        driver.get("https://eventhub.rahulshettyacademy.com/");
        signIn("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        //driver.findElement(By.xpath("//button[normalize-space()='My Bookings']")).click();
        Thread.sleep(5000);
        System.out.println("Inside EventHUB Page");
    }

    public void signIn(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);

        WebElement SignInButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SignInButton);
    }

    @Test
    public void test2() {
        driver.get("https://www.ultimateqa.com");
        WebElement NewsLetter = driver.findElement(By.xpath("//a[@class='inline-flex items-center justify-center font-semibold rounded-lg transition-all duration-200 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed bg-transparent text-white border border-white/40 hover:border-white hover:bg-white/10 focus-visible:ring-white px-8 py-4 text-lg gap-2.5 w-full sm:w-auto']"));
        boolean IsDisplayed = NewsLetter.isDisplayed();
        Assert.assertTrue(IsDisplayed, "Error: Web Element not displayed");
        System.out.println("Text Link: " + NewsLetter.getText());
    }
}
