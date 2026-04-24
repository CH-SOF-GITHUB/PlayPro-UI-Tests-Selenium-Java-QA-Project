package PageObject;

import org.openqa.selenium.JavascriptExecutor;
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
    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' bg-secondary ') and contains(concat(' ', normalize-space(@class), ' '), ' shadow ')]")
    private WebElement RéservationsLink;

    @FindBy(xpath = "/html/body/div[1]/main/section[1]/div[2]/div[2]/div/a[1]")
    private WebElement RéserverLink;

    // @FindBy(xpath = "(//p[normalize-space()='Nos activités'])[1]")
    // private WebElement Nos_Activites_Text;

    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' rounded-md ') and contains(concat(' ', normalize-space(@class), ' '), ' font-poppins ')]")
    private WebElement Offre_Link;

    /* methods of POM class */
    public void ClickOffreMenuNavbar() {
        WebElement button = wait.until(ExpectedConditions.visibilityOf(Offre_Link));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Offre link clicked in navbar");
    }

    public void clickRéservationsLinkEnNavBar() {
        WebElement button = wait.until(ExpectedConditions.visibilityOf(RéservationsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Réservations link clicked in navbar");
    }

    public void clickRéserverLink() {
        WebElement button = wait.until(ExpectedConditions.visibilityOf(RéserverLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Réserver link clicked in navbar");
    }

    /* public void checkVisibilityOfNosActivitesText() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Nos_Activites_Text));
            wait.until(ExpectedConditions.elementToBeClickable(Nos_Activites_Text));
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    } */
}
