package PageObject.Abonnements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// page_url = about:blank
public class WebSubscriptionsPage {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;

    public WebSubscriptionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    // define web elements using @FindBy annotation
    @FindBy(xpath = "//*[@href = '/discover/serie']")
    private WebElement seriesLink;

    // define methods to interact with the web elements
    public void clickSeriesLink() throws InterruptedException {
        Thread.sleep(1000);
        WebElement link = wait.until(ExpectedConditions.visibilityOf(seriesLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        System.out.println("Series link clicked in navbar");
    }
}
