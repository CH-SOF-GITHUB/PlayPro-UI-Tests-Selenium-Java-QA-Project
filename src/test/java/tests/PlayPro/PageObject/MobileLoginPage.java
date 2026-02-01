package tests.PlayPro.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


// page_url = https://chakertestqa.playpro.fr/
public class MobileLoginPage {
    // define a web driver of POM class
    private WebDriver driver;
    // define explicit wait
    private final WebDriverWait wait;

    public MobileLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // web elements of POM class
    @FindBy(xpath = "//button[@id='headlessui-menu-button-:r1:']")
    private WebElement MobileMenuBtn;

    @FindBy(xpath = "//a[@id='headlessui-menu-item-:r9:']")
    private WebElement MonCompteBtn;

    @FindBy(xpath = "//input[@placeholder='Adresse email']")
    private WebElement EmailInput;

    @FindBy(xpath = "//input[@placeholder='Mot de passe']")
    private WebElement PasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Me connecter']")
    private WebElement LoginButton;

    @FindBy(xpath = "/html/body/div[3]/main/div/form/div[2]/button")
    private WebElement ShowPwdButton;

    @FindBy(xpath = "/html/body/div[3]/main/div/form/button")
    private WebElement ToForgotPwdButton;

    @FindBy(xpath = "/html/body/div[3]/main/div/div[2]/p/button")
    private WebElement ToSignUpButton;

    /* methods of POM class */

    public void ClickMenuButton() {
        wait.until(ExpectedConditions.visibilityOf(MobileMenuBtn));
        MobileMenuBtn.click();
    }

    public void ClickMonCompteButton() {
        wait.until(ExpectedConditions.visibilityOf(MonCompteBtn));
        MonCompteBtn.click();
    }

    public void EnterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(EmailInput));
        EmailInput.clear();
        EmailInput.sendKeys(email);
    }

    public void EnterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(PasswordInput));
        PasswordInput.clear();
        PasswordInput.sendKeys(password);
    }

    public void ClickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(LoginButton));
        LoginButton.click();
    }


    public void ClickShowPwdButton() {
        wait.until(ExpectedConditions.visibilityOf(ShowPwdButton));
        ShowPwdButton.click();
    }

    public void ClickToForgotPwdButton() {
        wait.until(ExpectedConditions.visibilityOf(ToForgotPwdButton));
        ToForgotPwdButton.click();
    }

    public void ClickToSignUpButton() {
        wait.until(ExpectedConditions.visibilityOf(ToSignUpButton));
        ToSignUpButton.click();
    }
}
