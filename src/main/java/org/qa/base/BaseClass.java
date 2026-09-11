package org.qa.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.utilities.ExtentManager;
import org.qa.utilities.LoggerManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
    private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();

    public static final Logger loggr = LoggerManager.getLogger(BaseClass.class);

    // Create the object thread local of soft assert or we can create instance: ThreadLocal.withInitial(SoftAssert::new);
    private static ThreadLocal<SoftAssert> softAsserts = ThreadLocal.withInitial(SoftAssert::new);


    @BeforeMethod
    public synchronized void setup() {
        try {
            // Set Settings up message
            System.out.println("Settings up for : " + this.getClass().getSimpleName());

            // Load properties configuration
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(file);
            loggr.info("properties.config File Loaded successfully");

            // Start the Extent Report:
            // ExtentManager.getReporter();  // This has been implemented in TestListener

            // Initialize the WebDriver based on the browser specified in the properties file
            String browser = prop.getProperty("browser");
            if (browser.equalsIgnoreCase("firefox")) {
                // driver = new FirefoxDriver();
                driver.set(new FirefoxDriver());
                // Register the driver for Extent Report
                ExtentManager.registerDriver(getDriver());
                loggr.info("FirefoxDriver Instance Initialized successfully");
            } else if (browser.equalsIgnoreCase("chrome")) {
                //driver = new ChromeDriver();
                driver.set(new ChromeDriver());
                ExtentManager.registerDriver(getDriver());
                loggr.info("ChromeDriver Instance Initialized successfully");
            } else if (browser.equalsIgnoreCase("edge")) {
                //driver = new EdgeDriver();
                driver.set(new EdgeDriver());
                ExtentManager.registerDriver(getDriver());
                loggr.info("EdgeDriver Instance Initialized successfully");
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            // Initialize Implicit Wait
            int timeout = Integer.parseInt(prop.getProperty("timeout"));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

            // Navigate the Base URL
            String url = prop.getProperty("url");
            driver.get().get(url);

            // static wait for 2 S
            staticWait(2);

            loggr.info("WebDriver Initialized and Browser Opened and Maximized");
            loggr.trace("This is a trace message");
            loggr.error("This is an error message");
            loggr.debug("This is a debug message");
            loggr.warn("This is an warning message");
            loggr.fatal("This is a fatal message");

            // Implement Singleton Design Pattern and Initialize action driver only once
            /*if (actionDriver == null) {
                actionDriver = new ActionDriver(driver);
                loggr.info("Action driver is created in Thread : {}", Thread.currentThread().getId());
            }*/
            // Initialize action driver for current thread
            actionDriver.set(new ActionDriver(getDriver()));
            loggr.info("ActionDriver Initialize form Thread {}", Thread.currentThread().getId());

            // Maximize the web driver
            new ActionDriver(getDriver()).maximizeWindow();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
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
        this.driver = driver;
    }

    @SuppressWarnings("lombok")
    public static Properties getProp() {
        return prop;
    }

    @SuppressWarnings("lombok")
    public void setProp(Properties prop) {
        this.prop = prop;
    }


    // Getter method for soft assert class
    @SuppressWarnings("lombok")
    public static SoftAssert getSoftAsserts() {
        return softAsserts.get();
    }
}
