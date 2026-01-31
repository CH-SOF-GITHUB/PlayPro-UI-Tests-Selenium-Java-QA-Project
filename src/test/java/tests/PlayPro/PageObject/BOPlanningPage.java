package tests.PlayPro.PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;


// page_url = https://chakertestqa-bo.playpro.fr/back-office/planning
public class BOPlanningPage {
    /* web driver of POM class */
    protected WebDriver driver;
    // page url
    public final String page_url = "https://chakertestqa-bo.playpro.fr/back-office/planning";
    // define explicit wait
    private final WebDriverWait wait;

    /* constructor of POM class */
    public BOPlanningPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div[1]/div/div/div[1]/div[4]/button")
    private WebElement EspacesDropdown;

    @FindBy(xpath = "/html/body/div[2]/main/div/div/div[1]/div/div/div[1]/div[4]/div/div[1]/div[2]/button[1]")
    private WebElement ToutcocherButton;

    @FindBy(xpath = "/html/body/div[2]/main/div/div/div[1]/div/div/div[1]/div[4]/div/div[1]/div[2]/button[2]")
    private WebElement EffacerButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[1]/div/div/div[1]/div[4]/button/span/span")
    protected WebElement NbOfEspaces;

    @FindBy(xpath = "/html/body/div[2]/main/div/div/div[1]/div/div/div[1]/div[5]/button")
    private WebElement FiltreButton;

    /* methods of POM class */
    public void clickEspacesDropdown() {
        wait.until(ExpectedConditions.visibilityOf(EspacesDropdown));
        EspacesDropdown.click();
    }

    public void clickToutcocherButton() {
        wait.until(ExpectedConditions.visibilityOf(ToutcocherButton));
        ToutcocherButton.click();
    }

    public void clickEffacerButton() {
        wait.until(ExpectedConditions.visibilityOf(EffacerButton));
        EffacerButton.click();
    }

    public String getNbOfEspaces() {
        wait.until(ExpectedConditions.visibilityOf(NbOfEspaces));
        return NbOfEspaces.getText();
    }

    public WebElement NbOfEspacesElement() {
        return NbOfEspaces;
    }

    /* Methods of filtres planning (to be continued) */
    public void ClickFiltreButton() {
        wait.until(ExpectedConditions.visibilityOf(FiltreButton));
        FiltreButton.click();
    }

    public String GetFiltreButtonText() {
        wait.until(ExpectedConditions.visibilityOf(FiltreButton));
        return FiltreButton.getText();
    }
}
