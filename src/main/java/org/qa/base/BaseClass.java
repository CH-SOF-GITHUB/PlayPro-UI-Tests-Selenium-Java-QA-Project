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


public class BaseClass {
    protected Properties prop;
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        try {
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
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
