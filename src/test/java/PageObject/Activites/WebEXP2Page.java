package PageObject.Activites;

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
public class WebEXP2Page {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;


    public WebEXP2Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    /**
     *  Experience Card Path
     */
    @FindBy(xpath = "//*[@href = '/discover/reservation/expert-vr']//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' shadow-lg ')]")
    private WebElement ExpertVRCard;


    /**
     * Methods of the page
     */
    public void clickExpertVRCard() throws InterruptedException {
        Thread.sleep(1000);
        WebElement card = wait.until(ExpectedConditions.visibilityOf(ExpertVRCard));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        System.out.println("Expert VR Card clicked");
    }
}
