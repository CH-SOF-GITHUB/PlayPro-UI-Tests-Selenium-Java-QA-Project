package org.qa.base;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.utilities.ExtentManager;
import org.qa.utilities.LoggerManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;

import static org.qa.utilities.LTStatus.addLambdaStepContext;
import static org.qa.utilities.LTStatus.markTestStatusViaJS;

public class BaseClass {
    protected static Properties prop;
    // protected static WebDriver driver; problem: web driver is not opened parallel: classes not tests in TestNG file.
    // private static ActionDriver actionDriver;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();
    // Create the object thread local of soft assert; or we can create instance: ThreadLocal.withInitial(SoftAssert::new);
    private static final ThreadLocal<SoftAssert> softAsserts = ThreadLocal.withInitial(SoftAssert::new);
    // Create the objet for log utilities
    public static final Logger loggr = LoggerManager.getLogger(BaseClass.class);

    // Directs the native Java Util Logger to ignore anything lower than SEVERE errors for Selenium
    static {
        // Directs the native Java Util Logger to ignore anything lower than SEVERE errors for Selenium
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
    }

    @BeforeSuite
    public void configProp() {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(file);
            loggr.warn("properties.config File Loaded successfully");
        } catch (Exception e) {
            loggr.error("Failed to load properties file", e);
            throw new RuntimeException("Failed to load properties.config file.");
        }
    }

    public synchronized void configBrowser(String browser, Method m) {
        try {
            // retrieve the specified keys{browser,url} from the properties file
            //String browser = getProp().getProperty("browser", "chrome");
            String url = getProp().getProperty("url");
            // Safer Boolean parsing to avoid ClassCastException for property: headless
            boolean isHeadless = Boolean.parseBoolean(getProp().getProperty("headless", "false"));
            // retrieve the specified keys {isGrid,gridUrl} from the properties file
            String gridURL = getProp().getProperty("gridUrl");
            boolean isGRID = Boolean.parseBoolean(getProp().getProperty("isGrid", "false"));
            // Safer Boolean parsing to avoid ClassCastException for properties: LambdaTest, LT_USERNAME, LT_ACCESS_KEY
            boolean isLambdaTest = Boolean.parseBoolean(getProp().getProperty("LambdaTest", "false"));
            String username = getProp().getProperty("LT_USERNAME");
            String authkey = getProp().getProperty("LT_ACCESS_KEY");
            String hub = getProp().getProperty("hub");
            /**/
            if (isGRID) {
                try {
                    if (browser.equalsIgnoreCase("firefox")) {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080", "--disable-notifications", "--no-sandbox", "--disable-dev-shm-usage");
                        driver.set(new RemoteWebDriver(new URL(gridURL), firefoxOptions));
                    } else if (browser.equalsIgnoreCase("chrome")) {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080", "--disable-notifications", "--no-sandbox", "--disable-dev-shm-usage");
                        driver.set(new RemoteWebDriver(new URL(gridURL), chromeOptions));
                    } else if (browser.equalsIgnoreCase("edge")) {
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080", "--disable-notifications", "--no-sandbox", "--disable-dev-shm-usage");
                        driver.set(new RemoteWebDriver(new URL(gridURL), edgeOptions));

                        loggr.warn("GRID RemoteWebDriver instance was created in headless mode.");
                    } else {
                        throw new IllegalArgumentException("Browser '" + browser + "' not supported for Grid Tests");
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            } else if (isLambdaTest) {
                try {
                    // ✅ Use LambdaTest W3C-compliant structure (LT:Options)
                    MutableCapabilities ltOptions = new MutableCapabilities();
                    ltOptions.setCapability("build", "LT_PlayPro_UI_Tests_Selenium_Java_TestNG");
                    ltOptions.setCapability("name", m.getName() + " - " + this.getClass().getName());
                    ltOptions.setCapability("platformName", "Windows 11");
                    ltOptions.setCapability("plugin", "maven");
                    ltOptions.setCapability("network", true);
                    ltOptions.setCapability("visual", true);
                    ltOptions.setCapability("w3c", true);
                    ltOptions.setCapability("video", true);
                    ltOptions.setCapability("console", true);
                    ltOptions.setCapability("terminal", true);
                    ltOptions.setCapability("devicelog", true);
                    // ✅ Chrome browser setup (works with Selenium 4.x)
                    ChromeOptions browserOptions = new ChromeOptions();
                    browserOptions.setCapability("browserVersion", "latest");
                    browserOptions.setCapability("LT:options", ltOptions);
                    // ✅ Web Driver setup
                    driver.set(new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions));
                    loggr.warn("LT RemoteWebDriver instance was created in headless mode.");
                } catch (Exception e) {
                    loggr.error("Unable to set LT capabilities: {}", e.getMessage());
                    e.fillInStackTrace();
                }
            } else {
                if (browser.equalsIgnoreCase("firefox")) {
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
                    loggr.info("FirefoxDriver Instance Initialized successfully On ------> {}", isHeadless ? "Headless Mode" : "Opening Browser");

                } else if (browser.equalsIgnoreCase("chrome")) {
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
                    loggr.info("ChromeDriver Instance Initialized successfully On -------> {}", isHeadless ? "Headless Mode" : "Opening Browser");

                } else if (browser.equalsIgnoreCase("edge")) {
                    EdgeOptions options = new EdgeOptions();
                    if (isHeadless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                        options.addArguments("--disable-notifications");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        /* ARGUMENTS A AJOUTER POUR FIXER L'ERREUR JENKINS :
                        options.addArguments("--remote-debugging-port=9222");
                        En modifiant le compte de connexion du service Jenkins pour utiliser votre session Windows,
                        le navigateur dispose enfin d'un profil utilisateur valide et des droits requis pour s'ouvrir.
                        Le correctif élimine le crash de démarrage d'Edge et résout définitivement l'erreur DevToolsActivePort.
                         */
                    }
                    driver.set(new EdgeDriver(options));
                    ExtentManager.registerDriver(getDriver());
                    loggr.info("EdgeDriver Instance Initialized successfully On ------> {}", isHeadless ? "Headless Mode" : "Opening Browser");

                } else {
                    throw new IllegalArgumentException("Browser not supported: " + browser);
                }
            }

            // Initialize Implicit Wait
            // int timeout = Integer.parseInt(prop.getProperty("timeout", "10"));
            // getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

            // Maximize the WebDriver
            if (!isHeadless & !isGRID & !isLambdaTest) {
                getDriver().manage().window().maximize();
            }

            // Navigate the Base URL
            getDriver().get(url);
            // Sleep time for 3 s
            staticWait(3);

            // loggr.info("WebDriver Initialized correctly and a Browser Opened and Maximized");
            // loggr.trace("This is a trace message");
            // loggr.error("This is an error message");
            // loggr.debug("This is a debug message");
            // loggr.warn("This is a warning message");
            // loggr.fatal("This is a fatal message");

            // Implement Singleton Design Pattern and Initialize action driver only once
            /*if (actionDriver == null) {
                actionDriver = new ActionDriver(driver);
                loggr.info("Action driver is created in Thread : {}", Thread.currentThread().getId());
            }*/
            // Initialize action driver for current thread

            actionDriver.set(new ActionDriver(getDriver()));
            loggr.warn("ActionDriver Initialized for Thread ---------------------> {}", Thread.currentThread().getId());
        } catch (Exception e) {
            loggr.error("Error during browser configuration", e);
            throw new RuntimeException("WebDriver initialization failed: " + e.getMessage());
        }
    }


    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser, Method m) {
        loggr.warn("Setting up for ---------------> : {}", this.getClass().getSimpleName());
        // Start the Extent Report:
        // ExtentManager.getReporter();  // This has been implemented in TestListener
        if (prop == null) {
            // call the method to load properties
            configProp();
        }
        // call the method to open the browser
        configBrowser(browser, m);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver.get() != null) {
            try {
                boolean isSuccess = result.getStatus() == ITestResult.SUCCESS;
                String remark = isSuccess ? "Test Passed Successfully" : "Test failed: " + result.getThrowable().getMessage();
                addLambdaStepContext(driver.get(), "Closing Session");
                markTestStatusViaJS(driver.get(), isSuccess, remark);
                driver.get().quit();
            } catch (Exception e) {
                loggr.error("Unable to quit browser", e);
            } finally {
                driver.remove();
                actionDriver.remove();
                softAsserts.remove();
                // Close wen driver for current thread
                // driver = null;
                // actionDriver = null;
                /* After each test , we call end test to flush the extent report */
                // ExtentManager.endTest();   // This has been implemented in TestListener
            }
        }
        loggr.warn("WebDriver instance is closed for ---------------> : {}", this.getClass().getSimpleName());
    }

    /* Static wait for pause:
    * When to use staticWait:
    * Waiting for AJAX calls to complete (though explicit waits are better)
    * Synchronization points between test steps
    * Delays needed for UI animations
        When to avoid:
        * General pauses in test flow
        * Replacing proper wait strategies
        * Making tests unnecessarily slow  */
    public void staticWait(int seconds) {
        LockSupport.parkNanos((TimeUnit.SECONDS.toNanos(seconds)));
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

    public static Properties getProp() {
        return prop;
    }

    // Getter method for soft assert class
    public static SoftAssert getSoftAsserts() {
        return softAsserts.get();
    }
}
