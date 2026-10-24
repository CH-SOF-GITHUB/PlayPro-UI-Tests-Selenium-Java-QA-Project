package Packt.automationTestingWithJavaAndSelenium.junitBasics;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qa.utilities.TakeCapture;

import java.io.IOException;
import java.time.Duration;

public class SauceDemoLoginTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";

    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By BOUTON_LOGIN = By.id("login-button");
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");
    private static final By LISTE_PRODUITS = By.className("inventory_list");

    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final String USERNAME_INVALIDE = "invalid_user";
    private static final String PASSWORD_INVALIDE = "wrong_password";

    private static final String ERREUR_USERNAME_REQUIS = "Epic sadface: Username is required";
    private static final String ERREUR_PASSWORD_REQUIS = "Epic sadface: Password is required";
    private static final String ERREUR_IDENTIFIANTS_INVALIDES = "Epic sadface: Username and password do not match any user in this service";

    // Define Web driver object
    private WebDriver driver;

    // Add an instance of TakeCapture class
    TakeCapture capture;

    @Rule
    public TestName testName = new TestName();

    // Setup Tests
    @Before
    public void setUp() {
        System.out.println("Starting Web Driver ...");
        try {
            WebDriverManager.getInstance().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
            capture = new TakeCapture(driver);
        } catch (WebDriverException e) {
            throw new WebDriverException("Web Driver not Initialized: " + e.getMessage());
        }
    }

    // Test Case 01 - successful login
    @Test
    public void testValidLogin() throws InterruptedException {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // sleep 3s to load page
        Thread.sleep(3000);
        // Assertion 1
        String actualUrl = getCurrentURL();
        boolean isUrl = actualUrl.contains("/inventory.html");
        Assert.assertTrue(isUrl);
        // Assertion 2
        boolean isProdsList = isElementDisplayed(LISTE_PRODUITS);
        Assert.assertTrue(isProdsList);
    }

    // Test Case 02 - Empty Username
    @Test
    public void testEmptyUsername() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_USERNAME_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 03 - Empty Password
    @Test
    public void testEmptyPassword() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_PASSWORD_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 04 - Username invalide
    @Test
    public void testInvalidUsername() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_INVALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_IDENTIFIANTS_INVALIDES);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 05 - Password invalide
    @Test
    public void testInvalidPassword() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_INVALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_IDENTIFIANTS_INVALIDES);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 06 - Empty Username and Password
    @Test
    public void testEmptyCredentials() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_USERNAME_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

    // tearDown Tests
    @After
    public void tearDown() throws IOException {
        if (driver != null) {
            System.out.println("I'm Executed");
            capture.takeScreenshot(String.valueOf(testName));
            driver.quit();
        }
    }

    // Actions
    public boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No such element : " + e.getMessage());
        }
    }

    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new NoSuchSessionException("Cannot retrieve current URL : " + e.getMessage());
        }
    }

    public String getElementText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }
}
