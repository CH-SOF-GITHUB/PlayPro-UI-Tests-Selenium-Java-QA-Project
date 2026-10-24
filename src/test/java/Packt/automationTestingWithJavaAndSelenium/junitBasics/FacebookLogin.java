package Packt.automationTestingWithJavaAndSelenium.junitBasics;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.fail;

public class FacebookLogin {
    private WebDriver driver;
    private String baseUrl;
    private final String fbEmail = "bchaker28@yahoo.com";
    private final String fbPwd = "Facebook123!";
    private final By FbUserProfile = By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/a[1]/div[1]/div[1]");

    private final String internetUsername = "tomsmith";
    private final String internetPwd = "SuperSecretPassword!";
    private By SecureLoginEl = By.xpath("(//div[@id='flash'])[1]");

    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    /*
     * préparer automatiquement le ChromeDriver nécessaire pour contrôler le navigateur Google Chrome avec Selenium.
     * Elle utilise la bibliothèque WebDriverManager de Boni Garcia.
     * 1. Que fait cette ligne exactement ?
     *    1. ChromeDriverManager:  Sélectionne le gestionnaire destiné au navigateur Chrome.
     *    2. getInstance():        Récupère une instance du gestionnaire ChromeDriver.
     *    3. setup():              Vérifie la disponibilité du pilote, télécharge la version appropriée
     *                             si nécessaire et configure son utilisation par Selenium.
     *
     * 2. Pourquoi est-ce nécessaire ?
     * Pour automatiser Chrome, Selenium a besoin de deux éléments :
     *     - Google Chrome : le navigateur à contrôler.
     *     - ChromeDriver : le composant qui permet à Selenium de communiquer avec Chrome.
     * Sans configuration correcte du pilote, la création de new ChromeDriver() peut échouer.
     * */

    @Before
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Ignore
    @Test
    public void testFacebookLogin() throws Exception {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(fbEmail);
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(fbPwd);
        driver.findElement(By.name("pass")).sendKeys(Keys.ENTER);
        // Sleep for 5s to load page
        Thread.sleep(50000);
        // Assertion 1
        String ExpectedTitle = "(20+) Facebook";
        String ActualTitle = driver.getTitle();
        boolean isTitleFb = ExpectedTitle.equals(ActualTitle);
        Assert.assertTrue(isTitleFb);
        // Assertion 2
        Assert.assertTrue(isElementPresent(FbUserProfile));
        // Assertion 3
        String ExpectedUserName = getElementText(FbUserProfile);
        boolean isUserName = ExpectedUserName.equals("Chaker Ben Said");
        Assert.assertTrue(isUserName);
    }

    @Test
    public void testTheInternetLogin() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(internetUsername);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(internetPwd);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        // Sleep for 5s to load the page
        Thread.sleep(5000);
        // Assertion 1
        boolean isLogged = isElementPresent(SecureLoginEl);
        Assert.assertTrue(isLogged);
        // Assertion 2
        String successLoginMsg = getElementText(SecureLoginEl);
        System.out.println("Secure Area Login message : " + successLoginMsg);
    }

    @Test
    public void testTheInternetAlert1() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts?utm_source=chatgpt.com");
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();
        // Vérifier la présence de l'alerte
        boolean isJsAlert = isAlertPresent();
        Assert.assertTrue(isJsAlert);
        // Thread.sleep(3000);
        // Accepter l'alerte séparément
        acceptAlert();
    }

    @Test
    public void testTheInternetAlert2() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts?utm_source=chatgpt.com");
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();
        // Vérifier la présence de l'alerte
        boolean isJsAlert = isAlertPresent();
        Assert.assertTrue(isJsAlert);
        // Thread.sleep(3000);
        // fermer alert et vérifier Alert Text
        String alertText = closeAlertAndGetItsText();
        System.out.println("Alert Text : " + alertText);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            System.out.println("I'm, Executed");
            driver.quit();
        }
        String verificationErrorString = verificationErrors.toString();
        if (!verificationErrorString.isEmpty()) {
            fail(verificationErrorString);
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getElementText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException("No Alert is present : " + e.getMessage());
        }
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException("No Alert is present : " + e.getMessage());
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException("No Alert is present : " + e.getMessage());
        } finally {
            acceptNextAlert = true;
        }
    }
}
