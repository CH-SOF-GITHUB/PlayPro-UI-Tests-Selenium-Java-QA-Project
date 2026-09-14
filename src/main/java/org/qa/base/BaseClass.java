package org.qa.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.qa.actionDriver.ActionDriver;
import org.qa.utilities.ExtentManager;
import org.qa.utilities.LoggerManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class BaseClass {
    protected static Properties prop;

    // protected static WebDriver driver;   (problem: web driver is not opened: parallel: classes not tests in tesntng file.
    // private static ActionDriver actionDriver;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();

    public static final Logger loggr = LoggerManager.getLogger(BaseClass.class);

    // Create the object thread local of soft assert; or we can create instance: ThreadLocal.withInitial(SoftAssert::new);
    private static final ThreadLocal<SoftAssert> softAsserts = ThreadLocal.withInitial(SoftAssert::new);


    @BeforeSuite
    public void configProp() {
        try {
            // Load properties configuration
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(file);
            loggr.info("properties.config File Loaded successfully");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public synchronized void configBrowser() {
        try {
            // Initialize the WebDriver based on the browser and url specified keys in the properties file
            String browser = getProp().get("browser").toString();
            String url = getProp().get("url").toString();


            if (browser.equalsIgnoreCase("firefox")) {
                // driver = new FirefoxDriver();
                // Update WebDriver for browser execution to headless mode
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new");                // Run Chrome in headless mode
                options.addArguments("--disable-gpu");                 // Disable GPU for headless mode
                options.addArguments("--window-size=1920,1080");       // Set window size
                options.addArguments("--disable-notifications");       // Disable browser notifications
                options.addArguments("--no-sandbox");                  // Required for some CI environments like Jenkins
                options.addArguments("--disable-dev-shm-usage");       // Resolve issues in resource-limited environments
                driver.set(new FirefoxDriver(options));                // New Changes as per Thread
                // Register the driver for Extent Report
                ExtentManager.registerDriver(getDriver());
                loggr.info("FirefoxDriver Instance Initialized successfully");
            } else if (browser.equalsIgnoreCase("chrome")) {
                //driver = new ChromeDriver();
                // Update WebDriver for browser execution to headless mode
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");                // Run Chrome in headless mode
                options.addArguments("--disable-gpu");                 // Disable GPU for headless mode
                options.addArguments("--window-size=1920,1080");       // Set window size
                options.addArguments("--disable-notifications");       // Disable browser notifications
                options.addArguments("--no-sandbox");                  // Required for some CI environments like Jenkins
                options.addArguments("--disable-dev-shm-usage");       // Resolve issues in resource-limited environments
                driver.set(new ChromeDriver(options));                 // New Changes as per Thread
                ExtentManager.registerDriver(getDriver());
                loggr.info("ChromeDriver Instance Initialized successfully");
            } else if (browser.equalsIgnoreCase("edge")) {
                //driver = new EdgeDriver();
                // Update WebDriver for browser execution to headless mode
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");                // Run Chrome in headless mode
                options.addArguments("--disable-gpu");                 // Disable GPU for headless mode
                options.addArguments("--window-size=1920,1080");       // Set window size
                options.addArguments("--disable-notifications");       // Disable browser notifications
                options.addArguments("--no-sandbox");                  // Required for some CI environments like Jenkins
                options.addArguments("--disable-dev-shm-usage");       // Resolve issues in resource-limited environments
                driver.set(new EdgeDriver(options));                   // New Changes as per Thread
                ExtentManager.registerDriver(getDriver());
                loggr.info("EdgeDriver Instance Initialized successfully");
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            // Initialize Implicit Wait
            int timeout = Integer.parseInt(prop.getProperty("timeout"));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            // Maximize the web driver
            // driver.get().manage().window().maximize();

            // Navigate the Base URL
            driver.get().get(url);

            // static wait for 3 S
            staticWait(3);

            loggr.info("WebDriver Initialized and Browser Opened and Maximized");
            // loggr.trace("This is a trace message");
            // loggr.error("This is an error message");
            // loggr.debug("This is a debug message");
            // loggr.warn("This is an warning message");
            // loggr.fatal("This is a fatal message");

            // Implement Singleton Design Pattern and Initialize action driver only once
            /*if (actionDriver == null) {
                actionDriver = new ActionDriver(driver);
                loggr.info("Action driver is created in Thread : {}", Thread.currentThread().getId());
            }*/
            // Initialize action driver for current thread
            actionDriver.set(new ActionDriver(getDriver()));
            loggr.info("ActionDriver Initialize form Thread {}", Thread.currentThread().getId());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @BeforeMethod
    public synchronized void setup() {
        // Set Settings up message
        System.out.println("Settings up for : " + this.getClass().getSimpleName());
        // Start the Extent Report:
        // ExtentManager.getReporter();  // This has been implemented in TestListener
        // call load properties method
        configProp();
        // call lunch browser method
        configBrowser();
    }

    @AfterMethod
    public synchronized void tearDown() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception e) {
                System.out.println("unable to quit browser : " + e.getMessage());
            }
        }
        loggr.info("WebDriver instance is closed for :  {}", this.getClass().getSimpleName());

        driver.remove();
        actionDriver.remove();
        // Close wen driver for current thread
        // driver = null;
        // actionDriver = null;
        /* After each test , we call end test to flush the extent report */
        // ExtentManager.endTest();   // This has been implemented in TestListener
    }

    /* Static wait for pause:
    * When to use staticWait:
    * Waiting for AJAX calls to complete (though explicit waits are better)
    * Synchronization points between test steps
    * Delays needed for UI animations
        When to avoid:
        * General pauses in test flow
        * Replacing proper wait strategies
        * Making tests unnecessarily slow
*  */
    public void staticWait(int seconds) {
        LockSupport.parkNanos((TimeUnit.SECONDS.toNanos(seconds)));
    }

    @SuppressWarnings("lombok")
    /*public WebDriver getDriver() {
        return driver;
    }*/
    public static WebDriver getDriver() {
        // Use ThreadLocal for parallel testing
        if (driver.get() == null) {
            System.out.print("WebDriver is not initialized !!");
            throw new IllegalStateException("WebDriver is not initialized !!");
        }
        return driver.get();
    }

    public static ActionDriver getActionDriver() {
        // Use ThreadLocal for parallel testing
        if (actionDriver.get() == null) {
            System.out.print("Action Driver is not initialized !!");
            throw new IllegalStateException("Action Driver is not initialized !!");
        }
        return actionDriver.get();
    }

    @SuppressWarnings("lombok")
    public void setDriver(ThreadLocal<WebDriver> driver) {  // public void setDriver(WebDriver driver) in current thread
        BaseClass.driver = driver;
    }

    @SuppressWarnings("lombok")
    public static Properties getProp() {
        return prop;
    }

    @SuppressWarnings("lombok")
    public void setProp(Properties prop) {
        BaseClass.prop = prop;
    }


    // Getter method for soft assert class
    @SuppressWarnings("lombok")
    public static SoftAssert getSoftAsserts() {
        return softAsserts.get();
    }
}
