package configuration;

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
    // declare username and access key of lambdatest cloud account
    public static final String Username = "LambdaTest02outlook";
    public static final String AccessKey = "LT_OeKIsPcFeDNbCXdCF8F61YHX4U4JIW0qFsvvCM4804D4Cuz";
    // declare URL of lambdatest selenium grid
    public static final String gridURL = "https://" + Username + ":" + AccessKey + "@hub.lambdatest.com/wd/hub";
    // Remote server URL For Katalon Studio
    // String URL = "https://chakerlt1:LT_CbXl7y5tdDqGXPrCFWXXcWBzIuJVPrjEeVvZvOmdFTGBs69@hub.lambdatest.com/wd/hub";
    // declare a constructor of class
    public LTConfig() {

    }

    public static String getBuildName(int nb) {
        String name = null;
        try {
         switch (nb)  {
             case 1:
                    name = "BuildLT-BackOffice-PlayProV3-1.0.0";
                    break;
             case 2:
                    name = "BuildLT-Front-PlayProV3-1.0.0";
                    break;
             case 3:
                   name = "BuildLT-DataDriven-PlayProV3-1.0.0";
                   break;
             default:
                 return null;
         }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public static RemoteWebDriver getLTDriver() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        caps.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        caps.setCapability(CapabilityType.PLATFORM_NAME, "WIN11");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "UI-PlayPro-Testing");
        ltOptions.put("w3c", true);
        ltOptions.put("build", getBuildName(2));
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("screenshot", true);
        ltOptions.put("console", true);
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);
        caps.setCapability("lt:options", ltOptions);
        try {
            return new RemoteWebDriver(new URL(gridURL), caps);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Error is generated : ", e);
        }
    }

    // get screenshot of a web element
/*
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
*/

}
