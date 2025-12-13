package config;

import com.testingbot.testingbotrest.TestingbotUnauthorizedException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

public class ConfigSauceLabs {
    public static final String username = "Chaker";
    public static final String accessKey = "28ebe293-ad82-45b6-b5eb-38011fd46e88";
    // declare a remote web driver
    RemoteWebDriver driver = null;

    public ConfigSauceLabs() {
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("name", "PlayPro Automation Tests");
        sauceOptions.setCapability("tags", "tag1");
        sauceOptions.setCapability("build", "build-sauce-labs-1.0");
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("sauce:options", sauceOptions);
        chromeOptions.setCapability("platformName", "Windows 10");
        chromeOptions.setCapability("browserVersion", "latest");
        driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub"), chromeOptions);
        return driver;
    }
}
