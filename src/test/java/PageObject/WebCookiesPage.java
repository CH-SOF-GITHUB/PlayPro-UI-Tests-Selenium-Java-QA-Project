package PageObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

// page_url = about:blank
public class WebCookiesPage {
    private static final Log log = LogFactory.getLog(WebCookiesPage.class);
    /* web driver of POM class */
    private final WebDriver driver;

    private final WebDriverWait wait;

    public WebCookiesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "//button[@data-testid=\"cookie-banner-accept-button\"]")
    private WebElement AcceptCookiesButton;
    // xpath = "//div[@class='c15t-footerSubGroup-HbTp3']//button[@class='c15t-button-YKOgW c15t-button-small-n5LJg c15t-button-primary-stroke-TWzjH'][normalize-space()='Accepter']"

    /* methods of POM class */
    public void clickAcceptCookiesButton() {
        try {
            wait.until(ExpectedConditions.visibilityOf(AcceptCookiesButton));
            AcceptCookiesButton.click();
            log.info("Accepted cookies successfully.");
        } catch (Exception e) {
            log.warn("Accept Cookies button not found or not clickable: " + e.getMessage());
        }

    }
}
