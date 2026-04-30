package PageObject;

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
public class WebFoodPage {
    WebDriver driver;
    WebDriverWait wait;

    public WebFoodPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    /* *
     * WebElements
     * */
    @FindBy(xpath = "//*[@href = '/discover/food']")
    private WebElement foodLink;

    @FindBy(xpath = "//div[@role='alert']//div[contains(text(),\"L'établissement est fermé\")]")
    private WebElement restaurantClosedAlert;

    /* *
     * Methods
     * */
    public void clickFoodLink() throws InterruptedException {
        Thread.sleep(1000);
        WebElement link = wait.until(ExpectedConditions.visibilityOf(foodLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        System.out.println("Food link clicked in navbar");
    }

    public void isRestaurantClosedAlertDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        WebElement alert = wait.until(ExpectedConditions.visibilityOf(restaurantClosedAlert));
        boolean isDisplayed = alert.isDisplayed();
        System.out.println("Restaurant closed alert displayed: " + isDisplayed);
    }

    public String getRestaurantClosedAlertText() throws InterruptedException {
        Thread.sleep(1000);
        WebElement alert = wait.until(ExpectedConditions.visibilityOf(restaurantClosedAlert));
        return alert.getText();
    }
}
