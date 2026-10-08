package com.qa.cucumber.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Contract;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.qa.utilities.LTStatus.addLambdaStepContext;
import static org.qa.utilities.LTStatus.markTestStatusViaJS;


public class Hooks {
    private static final Log log = LogFactory.getLog(Hooks.class);
    // initialize the web driver
    // public static WebDriver driver = null;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();
    // initialize the Properties object
    protected static Properties prop;

    // Create the objet for log utilities
    // public static final org.apache.logging.log4j.Logger loggr = LoggerManager.getLogger(BaseClass.class);

    // AJOUT : ThreadLocal permet d'avoir un browser indépendant pour chaque test parallèle
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    static {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    @Before
    public void setUp() throws IOException {
        try {
            log.warn("🚀... Starting WebDriver For Cucumber BDD ...");

            // remplacer le chargement de prop par BaseClass.configProp() (nullpointer exception)
            BaseClass.configProp();

            log.warn("properties.config File Loaded successfully");

            boolean isHeadless = Boolean.parseBoolean(BaseClass.getProp().getProperty("headless", "false"));

            // AJOUT : récupérer le navigateur configuré par le paramètre TestNG
            String browser = getBrowser();
            // AJOUT : vérifier que le paramètre browser a bien été transmis
            if (browser == null || browser.isBlank()) {
                throw new IllegalStateException(
                        "Browser parameter was not provided by TestNG."
                );
            }

            if (browser.equalsIgnoreCase("chrome")) {
                // initialize the ChromeOptions object
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }
                driver.set(new ChromeDriver(options));
                ExtentManager.registerDriver(getDriver());
                log.info("ChromeDriver Instance Initialized successfully For Cucumber On -------> {}" + (isHeadless ? "Headless Mode" : "Normal Mode"));
            } else if (browser.equalsIgnoreCase("firefox")) {
                // initialize the FirefoxOptions object
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }
                driver.set(new FirefoxDriver(options));
                ExtentManager.registerDriver(getDriver());
                log.info("FirefoxDriver Instance Initialized successfully For Cucumber On -------> {}" + (isHeadless ? "Headless Mode" : "Normal Mode"));
            } else if (browser.equalsIgnoreCase("edge")) {
                // initialize the EdgeOptions object
                EdgeOptions options = new EdgeOptions();
                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                }
                driver.set(new EdgeDriver(options));
                ExtentManager.registerDriver(getDriver());
                log.info("EdgeDriver Instance Initialized successfully For Cucumber On -------> {}" + (isHeadless ? "Headless Mode" : "Normal Mode"));
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }

            // Maximize the WebDriver window only if not in headless mode
            if (!isHeadless) {
                driver.get().manage().window().maximize();
            }

            // Initialize the ActionDriver object
            actionDriver.set(new ActionDriver(getDriver()));

            /* Puisque ton ActionDriver est conçu autour de BaseClass, il faut que le WebDriver créé par Cucumber soit également enregistré dans BaseClass. * */
            BaseClass.setDriver(driver.get());

            log.warn("ActionDriver Instance Initialized successfully For Cucumber Methods BDD");

            /* The return value of "ExtentManager.getTest()" is null because of logSteps contain ActionDriver Class
            logFailureWithScreenshot is called from ActionDriver Class, so we need to register the driver in ExtentManager
            Quand TestNG démarre -> ExtentTest est créé pour la méthode Runner TestNG.
            Quand Hooks @Before s'exécute -> ExtentTest est écrasé par le test Cucumber.
            */
            ExtentManager.startTest("Cucumber - " + Thread.currentThread().getId());
        } catch (Exception e) {
            log.error("\n🚀... Initialization of CUCUMBER Web Driver Fails: " + e.getMessage());
            throw new RuntimeException("Initialization of CUCUMBER Web Driver Fails: " + e.getMessage());
        }
    }


    @After
    public void tearDown(Scenario scenario) throws IOException, InterruptedException {
        log.warn("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        // Screenshot en cas de succès
        try {
            // Captures d'écran locales selon le statut
            if (getDriver() != null) {
                File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                if (scenario.getStatus() == Status.PASSED) {
                    FileUtils.copyFile(srcFile, new File("src/test/java/com/qa/cucumber/bdd/Screenshots/success/scenarioPassed_" + scenarioName + ".png"));
                } else if (scenario.getStatus() == Status.FAILED) {
                    FileUtils.copyFile(srcFile, new File("src/test/java/com/qa/cucumber/bdd/Screenshots/failure/scenarioFailed_" + scenarioName + ".png"));
                } else if (scenario.getStatus() == Status.SKIPPED) {
                    FileUtils.copyFile(srcFile, new File("src/test/java/com/qa/cucumber/bdd/Screenshots/skipped/scenarioSkipped_" + scenarioName + ".png"));
                }
            }
        } catch (Exception e) {
            log.error("Erreur lors de la capture d'écran dans Hooks: " + e.getMessage());
        }

        // LambdaTest uniquement
        boolean isLambdaTest = Boolean.parseBoolean(BaseClass.getProp().getProperty("LambdaTest", "false"));

        if (isLambdaTest) {
            String status;
            if (scenario.getStatus() == Status.PASSED) {
                status = "passed";
            } else if (scenario.getStatus() == Status.FAILED) {
                status = "failed";
            } else {
                status = "skipped";
            }
            String remark = "Scenario " + scenario.getName() + " " + status;
            addLambdaStepContext(driver.get(), "Closing Session");
            markTestStatusViaJS(getDriver(), status, remark);
        }

        // Flush du rapport Extent Reports
        ExtentManager.endTest();

        // Fermeture du navigateur
        if (driver.get() != null) {
            log.info("\n 🚀... Closing WebDriver For Cucumber BDD...");
            driver.get().quit();
            driver.remove();
            actionDriver.remove();
        }
    }

    // Getter method to access the Properties object
    @Contract(pure = true)
    public static Properties getProp() {
        return prop;
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized for thread: " + Thread.currentThread().getId());
        }
        return driver.get();
    }

    // public void setDriver(WebDriver driver) in current thread
    public static ActionDriver getActionDriver() {
        if (actionDriver.get() == null) {
            throw new IllegalStateException("Action Driver is not initialized for thread: " + Thread.currentThread().getId());
        }
        return actionDriver.get();
    }

    // AJOUT : stocker le navigateur par Thread pour supporter l'exécution parallèle
    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

    // AJOUT : récupérer le navigateur associé au thread courant
    public static String getBrowser() {
        return browser.get();
    }
}
