package config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class TBConfigEdge {
    // declare a remote web driver
    public static RemoteWebDriver driver = null;

    // declare for capabilities
    public static final String KEY = "4f978bbf1c5989cf2b2a8ee530d278d2";
    public static final String SECRET = "17e87457ac005023c357a117980c3b9f";

    // URL Selenium Grid de TestingBot Edge
    public static final String GRID_EDGE_URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";

    // Tunnel Selenium Grid de TestingBot Edge
    public static final String tunnelURL = "http://" + KEY + ":" + SECRET + "@localhost:4445/wd/hub";

    // declare a constructor class
    public TBConfigEdge() {
    }

    public static RemoteWebDriver getDriver() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", "microsoftedge");
        caps.setCapability("browserVersion", "142");
        caps.setCapability("platformName", "WIN11");
        int Num1 = ThreadLocalRandom.current().nextInt(1, 10);
        int Num2 = ThreadLocalRandom.current().nextInt(0, 10);
        int Num3 = ThreadLocalRandom.current().nextInt(0, 10);
        HashMap<String, Object> tbOptions = new HashMap<>();
        tbOptions.put("edgeVersion", "120");
        tbOptions.put("build", "Edge-Build-version-" + Num1 + "." + Num2 + "." + Num3);
        tbOptions.put("tunnel", true);
        tbOptions.put("screenshot", true);
        caps.setCapability("tb:options", tbOptions);
        // URL Selenium Grid de TestingBot Edge
        try {
            return new RemoteWebDriver(new URL(GRID_EDGE_URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid URL provided for TestingBot hub", e);
        }
    }
}
