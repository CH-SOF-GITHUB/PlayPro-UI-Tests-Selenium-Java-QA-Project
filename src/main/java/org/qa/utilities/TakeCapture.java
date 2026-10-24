package org.qa.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TakeCapture {

    private final WebDriver driver;

    public TakeCapture(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String title) throws IOException {
        // Initialize an instance to the TakeCapture class
        TakesScreenshot ts = (TakesScreenshot) driver;
        // Capture screenshot as output type FILE
        File file = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("E:\\DESKTOP\\automation-projects\\circleci-test\\PlayPro-UI-Tests-Selenium-Java-QA-Project\\src\\test\\resources\\screenshots\\" + title + ".png"));
    }

}
