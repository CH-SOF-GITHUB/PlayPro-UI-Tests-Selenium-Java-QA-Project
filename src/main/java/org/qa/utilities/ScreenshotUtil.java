package org.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
    /**
     * Convertit un fichier screenshot en chaîne Base64 en utilisant Apache Commons IO
     *
     * @param screenshotFile Fichier screenshot à convertir
     * @return Chaîne Base64 du contenu du fichier ou null en cas d'échec
     */
    public static String convertFileToBase64(File screenshotFile) {
        String base64Format = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenshotFile);
            base64Format = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            System.out.println("Failed to convert file to Base64: " + e.getMessage());
            e.fillInStackTrace();
        }
        return base64Format;
    }

    /**
     * Prend une capture d'écran, la sauvegarde avec horodatage et la convertit en Base64
     *
     * @param driver WebDriver instance
     * @param screenshotName Nom de base pour le fichier screenshot
     * @return Chaîne Base64 de la capture d'écran ou null en cas d'échec
     */
    public synchronized static String takeScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        // Format date and Time for file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Saving the screenshot to a file
        String destPath = System.getProperty("user.dir") + "/src/test/resources/extentReports/screenshots/" + screenshotName + "_" + timeStamp + ".png";

        File finalPath = new File(destPath);
        try {
            FileUtils.copyFile(src, finalPath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot file: " + e.getMessage());
            e.fillInStackTrace();
        }

        // Convert screenshot to Base64 for embedding in the Report
        return convertFileToBase64(finalPath);
    }
}
