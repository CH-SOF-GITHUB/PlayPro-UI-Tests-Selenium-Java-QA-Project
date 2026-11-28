package com.qa.Cucumber_Acceptance_Tests.hooks;

import com.qa.Cucumber_Acceptance_Tests.context.BrowserSession;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserHooks {
    Properties props = new Properties();

    @Before
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        try {
            String base_url = getProperties("swagLabs.url");
            driver.get(base_url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BrowserSession.setDriver(driver);
    }

    @After
    public void stopBrowser() {
        try {
            WebDriver driver = BrowserSession.getDriver();
            driver.quit();
        } catch (IllegalStateException ignored) {
            // Browser startup may have failed before a session was registered.
        } finally {
            BrowserSession.clear();
        }
    }

    public String getProperties(String key) throws FileNotFoundException {
        String result = null;
        String file = "src/test/java/com/qa/Cucumber_Acceptance_Tests/config/acceptance.properties";
        InputStream input = new FileInputStream(file);
        try {
            props.load(input);
            result = props.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
