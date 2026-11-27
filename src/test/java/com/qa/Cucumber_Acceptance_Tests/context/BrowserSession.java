package com.qa.Cucumber_Acceptance_Tests.context;

import org.openqa.selenium.WebDriver;

public final class BrowserSession {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private BrowserSession() { }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("The browser has not been initialized for this scenario.");
        }
        return driver;
    }

    public static void clear() {
        DRIVER.remove();
    }
}
