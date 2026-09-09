package org.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class BaseClass {
    protected static Properties prop;

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        try {
            System.out.println("Settings up for : " + this.getClass().getSimpleName());

            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(file);

            // Initialize the WebDriver based on the browser specified in the properties file
            String browser = prop.getProperty("browser");
            if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            // Initialize Implicit Wait
            int timeout = Integer.parseInt(prop.getProperty("timeout"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

            // Maximize the web driver
            driver.manage().window().maximize();

            // Navigate the Base URL
            String url = prop.getProperty("url");
            driver.get(url);

            // static wait for 2 S
            staticWait(2);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("unable to quit browser : " + e.getMessage());
            }
        }
    }

    /* Static wait for pause:
    * When to use staticWait:
    * Waiting for AJAX calls to complete (though explicit waits are better)
    * Synchronization points between test steps
    * Delays needed for UI animations
        When to avoid:
        * General pauses in test flow
        * Replacing proper wait strategies
        * Making tests unnecessarily slow
*  */
    public void staticWait(int seconds) {
        LockSupport.parkNanos((TimeUnit.SECONDS.toNanos(seconds)));
    }

    @SuppressWarnings("lombok")
    public WebDriver getDriver() {
        return driver;
    }

    @SuppressWarnings("lombok")
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @SuppressWarnings("lombok")
    public static Properties getProp() {
        return prop;
    }

    @SuppressWarnings("lombok")
    public void setProp(Properties prop) {
        this.prop = prop;
    }
}
