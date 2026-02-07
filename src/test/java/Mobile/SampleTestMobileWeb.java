package Mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SampleTestMobileWeb {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        MutableCapabilities caps = getMobileCapabilities();
        // Tes identifiants LambdaTest
        final String username = "LambdaTest02outlook";
        final String accessKey = "LT_OeKIsPcFeDNbCXdCF8F61YHX4U4JIW0qFsvvCM4804D4Cuz";

        // URL Appium LambdaTest(I don't use Appium (http://192.168.1.44:4723/) server locally, I use the one of LambdaTest cloud)
        // APK / App Android : AndroidDriver
        // iOS App : IOSDriver
        // Site web mobile : RemoteWebDriver
        // @mobile-hub.lambdatest.com/wd/hub
        WebDriver driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                caps
        );

        // Naviguer vers le site web mobile
        driver.get("https://chakertestqa.playpro.fr/");
        // attendre que la page se charge
        Thread.sleep(5000);

        // close the appium web driver
        driver.quit();
    }

    private static MutableCapabilities getMobileCapabilities() {
        // Device cloud : SAMSUNG Galaxy S23
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "safari");           // pour site web mobile         "safari"
        caps.setCapability(CapabilityType.BROWSER_VERSION, "18.6");          // version du navigateur        "18.6"
        caps.setCapability("appium:deviceName", "iPhone 16");  // choisir un device cloud réel "iPhone 16"
        caps.setCapability(CapabilityType.PLATFORM_NAME, "iOS");             // ou pour iPhone               "iOS"
        // caps.setCapability("resolution", "1080x2400");                           // optionnel, pour la console

        // capabilites of Lambdatest Cloud
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("isRealMobile", false);
        // ltOptions.put("automationName", "Appium");                             // Type d'automatisation pour les tests mobiles
        ltOptions.put("build", "BuildLT-Mobile-Example-Test");
        ltOptions.put("name", "LambdaTest Mobile Web Example Test");
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("console", true);
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);

        caps.setCapability("LT:Options", ltOptions);
        return caps;
    }
}
