package config;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LTConfig {
    // declare a Remote web driver as null
    static RemoteWebDriver driver = null;

    // declare username and access key of lambdatest cloud account
    public static final String Username = "chaker.nehos";
    public static final String AccessKey = "YzCmbZk6LHmgfa7wrkb6Asyr5IZKkmDGeu2V9CNtW07vyfkg4E";
    // declare URL of lambdatest selenium grid
    public static final String gridURL = "https://" + Username + ":" + AccessKey + "@hub.lambdatest.com/wd/hub";

    // declare a constructor of class
    public LTConfig() {

    }

    protected static RemoteWebDriver getLTDriver() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        caps.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        caps.setCapability(CapabilityType.PLATFORM_NAME, "WIN10");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "UI-PlayPro-Testing");
        ltOptions.put("w3c", true);
        ltOptions.put("build", "Build-LT-PlayProDemoV3-1.0.0");
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("screenshot", true);
        ltOptions.put("console", true);
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);
        caps.setCapability("lt:options", ltOptions);
        // URL Selenium Grid de TestingBot Edge
        try {
            return new RemoteWebDriver(new URL(gridURL), caps);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Error is generated : ", e);
        }
    }

    // get screenshot of a web element
    public static void getScreenshot(WebDriver driver, WebElement element, String fileName) throws IOException {

        // Create folder if not exist
        File dir = new File("src/test/java/tests/screenshots/");
        if (!dir.exists()) {
            dir.mkdirs(); // creates folder structure
        }

        // Take full page screenshot
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        BufferedImage fullImg = ImageIO.read(new ByteArrayInputStream(screenshotBytes));

        // Get element position and size
        Point point = element.getLocation();
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();

        // Safety check to avoid cropping outside image
        elementWidth = Math.min(elementWidth, fullImg.getWidth() - point.getX());
        elementHeight = Math.min(elementHeight, fullImg.getHeight() - point.getY());

        // Crop element screenshot
        BufferedImage elementScreenshot = fullImg.getSubimage(
                point.getX(),
                point.getY(),
                elementWidth,
                elementHeight
        );

        // Final path (CORRECTED)
        File screenshotLocation = new File(dir, fileName + ".png");

        // Save file
        ImageIO.write(elementScreenshot, "png", screenshotLocation);

        // Debug log (useful in GitHub Actions)
        System.out.println("📸 Screenshot saved at: " + screenshotLocation.getAbsolutePath());
    }

}
