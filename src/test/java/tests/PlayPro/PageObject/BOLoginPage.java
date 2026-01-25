package tests.PlayPro.PageObject;


import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


// page_url = about:blank

public class BOLoginPage {
    /* web driver of POM class */
    protected WebDriver driver;
    // page url
    public final String page_url = "https://chakertestqa-bo.playpro.fr/back-office/login";
    // define explicit wait
    private final WebDriverWait wait;

    /* constructor of POM class */
    public BOLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/form/input[2]")
    private WebElement BOEmailInput;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement BOPasswordInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/form/div[2]/label/input")
    private WebElement RememberMeCheckbox;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/form/button")
    private WebElement BOLoginButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/form/div[3]/a")
    private WebElement BOForgotPasswordLink;

    /* methods of POM class */
    public void enterBOEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(BOEmailInput));
        BOEmailInput.clear();
        BOEmailInput.sendKeys(email);
    }

    public void enterBOPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(BOPasswordInput));
        BOPasswordInput.clear();
        BOPasswordInput.sendKeys(password);
    }

    public void clickRememberMe() {
        wait.until(ExpectedConditions.visibilityOf(RememberMeCheckbox));
        RememberMeCheckbox.click();
    }

    public void clickBOLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(BOLoginButton));
        BOLoginButton.click();
    }

    public void clickBOForgotPasswordLink() {
        wait.until(ExpectedConditions.visibilityOf(BOForgotPasswordLink));
        BOForgotPasswordLink.click();
    }

    public void TakeScreenshot(String image) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/java/tests/Captures/" + image + ".png"));
    }
}
