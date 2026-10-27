package com.qa.factory;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestableDriverFactory {

    public TestableDriverFactory() {
    }

    public WebDriver createTestableDriver() {
        MutableCapabilities testableOpts = new MutableCapabilities();
        testableOpts.setCapability("user", "remote");
        testableOpts.setCapability("key", "jjxpwitiweykxbyjbvdoqvwslnextamniubepq");
        testableOpts.setCapability("region", "aws-us-east-1");
        testableOpts.setCapability("reportId", "release-v1");
        testableOpts.setCapability("tags", "smoke");

        ChromeOptions options = new ChromeOptions();
        options.setCapability("testable:options", testableOpts);
        options.setCapability("browserVersion", "Latest");

        try {
            return new RemoteWebDriver(new URL("https://selenium.testable.io/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
