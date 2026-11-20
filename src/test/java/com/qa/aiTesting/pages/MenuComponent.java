package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

/** Compatibility facade delegating shared menu actions to SideMenuPage. */
public class MenuComponent {
    private final SideMenuPage sideMenuPage;

    public MenuComponent(WebDriver driver, Duration timeout) {
        sideMenuPage = new SideMenuPage(driver, timeout);
    }

    public void open() { sideMenuPage.open(); }
    public void clickAbout() { sideMenuPage.clickAbout(); }
    public boolean isAboutPage() { return sideMenuPage.isAboutDestination(); }
    public void logout() { sideMenuPage.logout(); }
    public void resetAppState() { sideMenuPage.resetAppState(); }
}
