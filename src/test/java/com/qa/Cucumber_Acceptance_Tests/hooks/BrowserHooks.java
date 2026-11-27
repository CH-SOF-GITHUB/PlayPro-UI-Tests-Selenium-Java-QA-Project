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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class BrowserHooks {
    /*private static final Path CONFIG_FILE = Path.of(
            "src", "test", "java", "com", "qa", "Cucumber_Acceptance_Tests",
            "config", "acceptance.properties"
    );
    private static final String URL_PROPERTY = "swagLabs.url";*/

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
    /*private static String applicationUrl() {
        String overriddenUrl = System.getProperty(URL_PROPERTY);
        if (overriddenUrl != null && !overriddenUrl.isBlank()) {
            return overriddenUrl;
        }

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read acceptance configuration file: " + CONFIG_FILE, e);
        }

        String configuredUrl = properties.getProperty(URL_PROPERTY);
        if (configuredUrl == null || configuredUrl.isBlank()) {
            throw new IllegalStateException("Missing '" + URL_PROPERTY + "' in " + CONFIG_FILE);
        }
        return configuredUrl;
    }*/
}
