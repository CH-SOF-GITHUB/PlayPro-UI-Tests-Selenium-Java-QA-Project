package tests.PlayPro.PageObject;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BOBasePage {
    /* web driver of POM class */

    /* constructor of POM class */

    public BOBasePage() {
    }

    /* methods of POM class */
    public void TakeScreenshot(WebDriver driver) throws IOException {
        Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        ImageIO.write(myScreenshot.getImage(), "PNG", new File("src/test/java/tests/Captures/firefoxScreenshot.png"));
    }
}
