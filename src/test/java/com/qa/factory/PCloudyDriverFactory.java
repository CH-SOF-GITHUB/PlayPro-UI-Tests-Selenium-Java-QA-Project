package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PCloudyDriverFactory {

    public PCloudyDriverFactory() {
    }

    public WebDriver createPcloudyDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        HashMap<String, Object> pcloudyOptions = new HashMap<String, Object>();
        pcloudyOptions.put("userName", "8e089c9f-e6e6-4a2e-a20c-060f3fcec817@anonaddy.me");
        pcloudyOptions.put("accessKey", "j8jmnmmsvb64dgmyzswxrzbb");
        pcloudyOptions.put("os", "Windows");
        pcloudyOptions.put("osVersion", "11");
        pcloudyOptions.put("browserVersion", "155");
        pcloudyOptions.put("clientName", "Chaker Ben Said");
        pcloudyOptions.put("vmId", "7c:ed:8d:cc:11:8a");
        pcloudyOptions.put("seleniumVersion", "4.26.0");
        pcloudyOptions.put("project", "SauceDemo_Login_Regression_v1.2");
        pcloudyOptions.put("build", "SauceDemoLoginTest_Build_03");
        pcloudyOptions.put("name", "JunitTestEachProductHasNonEmptyTitle");
        pcloudyOptions.put("tag", "saucedemo");
        pcloudyOptions.put("local", false);
        pcloudyOptions.put("pCloudy_EnableVideo", true);
        capabilities.setCapability("pcloudy:options", pcloudyOptions);
        try {
            return new RemoteWebDriver(new URL("https://browser.device.pcloudy.com/seleniumcloud/wd/hub"), capabilities);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
