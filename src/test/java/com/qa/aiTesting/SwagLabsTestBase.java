package com.qa.aiTesting;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.aiTesting.pages.CartPage;
import com.qa.aiTesting.pages.CheckoutPage;
import com.qa.aiTesting.pages.FooterPage;
import com.qa.aiTesting.pages.InventoryPage;
import com.qa.aiTesting.pages.LoginPage;
import com.qa.aiTesting.pages.MenuComponent;
import com.qa.aiTesting.pages.ProductDetailsPage;
import com.qa.aiTesting.pages.SideMenuPage;

/** Shared browser setup and page objects for Swag Labs TestNG scenarios. */
public abstract class SwagLabsTestBase {
    protected static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected FooterPage footerPage;
    protected MenuComponent menu;
    protected SideMenuPage sideMenuPage;

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));
        options.addArguments("--incognito", "--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver, WAIT_TIMEOUT);
        inventoryPage = new InventoryPage(driver, WAIT_TIMEOUT);
        productDetailsPage = new ProductDetailsPage(driver, WAIT_TIMEOUT);
        cartPage = new CartPage(driver, WAIT_TIMEOUT);
        checkoutPage = new CheckoutPage(driver, WAIT_TIMEOUT);
        footerPage = new FooterPage(driver, WAIT_TIMEOUT);
        menu = new MenuComponent(driver, WAIT_TIMEOUT);
        sideMenuPage = new SideMenuPage(driver, WAIT_TIMEOUT);
        loginPage.open();
    }

    protected void loginAsStandardUser() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.waitUntilLoaded();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
