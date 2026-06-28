package com.qa.tests.PlayPro.DEV;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class SampleAllureTest {

    @Test
    @Description("This Test attempts to log into the website using a login and a password. Fails if any errors happens.\\n\\nNote that this test does not test 2-Factor Authentication.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    @Link(name = "My Site Web", url = "https://mysite.com")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void TestMySiteWeb() throws InterruptedException {
        System.out.println("Testing My Site Web ok");
        Thread.sleep(10000);
    }
}
