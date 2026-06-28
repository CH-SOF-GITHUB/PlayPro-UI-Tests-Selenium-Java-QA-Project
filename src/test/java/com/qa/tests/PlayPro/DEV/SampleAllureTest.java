package com.qa.tests.PlayPro.DEV;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SampleAllureTest {

    @Test
    @Description("This Test attempts to log into the website using a login and a password. Fails if any errors happens.\\n\\nNote that this test does not test 2-Factor Authentication.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    @Link(name = "My Site Web", url = "https://mysite.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void TestMySiteWeb() throws InterruptedException, IOException {
        System.out.println("Testing My Site Web ok");
        Thread.sleep(10000);
        Allure.attachment("data.txt", "This is the file content.");
        try (InputStream is = Files.newInputStream(Paths.get("src/test/java/com/qa/tests/Screenshots/allure/img.png"))) {
            Allure.attachment("image.png", is);
        }
    }
}
