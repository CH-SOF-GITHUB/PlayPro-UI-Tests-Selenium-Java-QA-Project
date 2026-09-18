package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

public class WindowHandlesTest {


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");
        // fetch handle of this ID de la fenêtre/onglet courant.
        // Chaque fenêtre/onglet possède son propre window handle: String mainWindow = driver.getWindowHandle();
        // Quand plusieurs onglets sont ouverts, Selenium doit savoir sur lequel travailler.
        // En cas tu ouvres un nouvel onglet; Pour revenir au premier: driver.switchTo().window(mainWindow);

        String currHandle = driver.getWindowHandle();
        System.out.println("Title Page 1: " + driver.getTitle() + " With Handle: " + currHandle);

        Thread.sleep(5);

        //System.out.println("Current Handle Of Page 1: " + currHandle);

        //click on link to open a new window
        driver.findElement(By.linkText("Open new window")).click();
        Thread.sleep(5);

        //System.out.println("Title Page 2: " + driver.getTitle());

        //fetch handles of all windows, there will be two, [0]- default, [1] - new window
        Object[] windowHandles = driver.getWindowHandles().toArray();
        System.out.println("windowHandles Object[] : " + Arrays.toString(windowHandles));

        driver.switchTo().window((String) windowHandles[1]);
        //assert on title of new window
        System.out.println("Title Page 2: " + driver.getTitle() + " With Handle: " + (String) windowHandles[1]);
        System.out.println("TEXT OF CURRENT PAGE: " + driver.findElement(By.cssSelector("body div:nth-child(1)")).getText());
        Thread.sleep(5);
        // closing the current window
        driver.close();

        // Switch back to the old tab or window
        driver.switchTo().window((String) windowHandles[0]);
        System.out.println("Title Of Current Page: " + driver.getTitle() + " With Handle: " + (String) windowHandles[0]);
        Thread.sleep(5);

        //Opens a new tab and switches to new tab
        driver.switchTo().newWindow(WindowType.TAB);
        assertEquals("", driver.getTitle());
        Thread.sleep(5);

        //Opens a new window and switches to new window
        driver.switchTo().newWindow(WindowType.WINDOW);
        assertEquals("", driver.getTitle());
    }
}
