package Selenide.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SimpleReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.selenide.videorecorder.core.VideoRecorder;

public class Hooks {
    private final SimpleReport report = new SimpleReport();
    // private VideoRecorder videoRecorder;

    @Before
    public void beforeTest(Scenario scenario) {
        scenario.log("Starting Selenide cucumber test: " + scenario.getName());
        Configuration.baseUrl = "https://demotenant.playpro.fr";
        Configuration.reportsFolder = "target/selenide-cucumber/reports";
        Configuration.downloadsFolder = "target/selenide-cucumber/downloads";
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--disable-blink-features=AutomationControlled");
        report.start();
        // Start video recording
        // videoRecorder = new VideoRecorder();
        // videoRecorder.startRecording();
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario failed: " + scenario.getName());
        } else {
            scenario.log("Scenario passed: " + scenario.getName());
        }

        report.finish("Finished Selenide cucumber test: " + scenario.getName());
    }
}
