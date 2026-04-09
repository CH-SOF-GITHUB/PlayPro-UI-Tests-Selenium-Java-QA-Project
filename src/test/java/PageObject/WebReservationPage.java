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

    @FindBy(xpath = "(//p[normalize-space()='Nos activités'])[1]")
    private WebElement Nos_Activites_Text;

    /* methods of POM class */
    public void clickRéservationsLinkEnNavBar() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(RéservationsLink));
            RéservationsLink.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickRéserverLink() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(RéserverLink));
            RéserverLink.click();
            Thread.sleep(4000);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void checkVisibilityOfNosActivitesText() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Nos_Activites_Text));
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
