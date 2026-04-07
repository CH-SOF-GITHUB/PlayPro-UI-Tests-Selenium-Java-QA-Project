package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


// page_url = https://chakertestqa.playpro.fr/connexion
public class WebReservationPage {
    /* web driver of POM class */
    private final WebDriver driver;

    private final WebDriverWait wait;

    public WebReservationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "/html/body/header/div/div[1]/ul/a[5]")
    private WebElement RéservationsLink;

    @FindBy(xpath = "/html/body/div[1]/main/section[1]/div[2]/div[2]/div/a[1]")
    private WebElement RéserverLink;

    /* methods of POM class */
    public void clickRéservationsLinkEnNavBar() {
        wait.until(ExpectedConditions.visibilityOf(RéservationsLink));
        RéservationsLink.click();
    }

    public void clickRéserverLink() {
        wait.until(ExpectedConditions.visibilityOf(RéserverLink));
        RéserverLink.click();
    }
}
