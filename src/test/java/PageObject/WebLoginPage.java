package PageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

// page_url = https://chakertestqa.playpro.fr/connexion
public class WebLoginPage {
    /* web driver of POM class */
    private final WebDriver driver;
    private final String LOGIN_URL = "https://demotenant.playpro.fr/connexion";
    private final WebDriverWait wait;

    public WebLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* first web elements of POM class */
    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' rounded-xl ')]")
    private WebElement EmailInput;

    @FindBy(xpath = "//*[@placeholder = 'Mot de passe']")
    private WebElement PasswordInput;

    @FindBy(xpath = "//*[@type = 'submit']")
    private WebElement LoginButton;

    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' relative ') and contains(concat(' ', normalize-space(@class), ' '), ' w-full ') and (name() = 'div') and (position() = 2)]//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' undefined ')]")
    private WebElement ShowPasswordButton;

    /* next web elements of POM class */
    @FindBy(xpath = "//button[normalize-space()='Mot de passe oublié ?']")
    private WebElement ForgotPasswordButton;

    @FindBy(xpath = "//button[@class='underline text-sky-400']")
    private WebElement SignUpButton;

    // errors messages
    @FindBy(xpath = "(//span[@class='text-xs leading-[0.625rem] font-normal text-[#DA1414]'])[1]")
    private WebElement LoginErrorMessage;

    @FindBy(xpath = "//span[normalize-space()='Adresse email est obligatoire']")
    private WebElement EmailRequiredErrorMessage;

    @FindBy(xpath = "//span[normalize-space()='Mot de passe est obligatoire']")
    private WebElement PasswordRequiredErrorMessage;

    /* methods of POM class */
    public void GoToLoginPage() {
        try {
            Thread.sleep(1000);
            driver.get(LOGIN_URL);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }


    public void EnterEmail(String email) throws InterruptedException {
        Thread.sleep(1000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(EmailInput));
        input.clear();
        input.sendKeys(email);
        System.out.println("Entered email: " + email);
    }

    public void EnterPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(PasswordInput));
        input.clear();
        input.sendKeys(password);
        System.out.println("Entered pwd: " + password);
    }

    public void ClickLoginButton() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(LoginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Clicked on Login button");
    }

    public void ClickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ForgotPasswordButton));
        ForgotPasswordButton.click();
    }

    public void ClickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SignUpButton));
        SignUpButton.click();
    }

    public void ClickShowPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ShowPasswordButton));
        ShowPasswordButton.click();
    }

    // getters for error messages
    public String GetLoginErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(LoginErrorMessage));
        return LoginErrorMessage.getText();
    }

    public String GetEmailRequiredErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(EmailRequiredErrorMessage));
        return EmailRequiredErrorMessage.getText();
    }

    public String GetPasswordRequiredErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(PasswordRequiredErrorMessage));
        return PasswordRequiredErrorMessage.getText();
    }

    /**
     * This function will take screenshot
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */
    public void takeSnapShot(WebDriver webdriver, String fileWithPath) throws RuntimeException, IOException {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
