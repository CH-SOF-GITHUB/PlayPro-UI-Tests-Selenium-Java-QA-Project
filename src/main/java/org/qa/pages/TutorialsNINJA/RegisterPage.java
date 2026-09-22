package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class RegisterPage {

    private ActionDriver actionDriver;

    public RegisterPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Locate wb elements depending on register new customer
    private final By loginPageLink = By.cssSelector("div[id='content'] p a");


    // Methods
    public void clickLoginPageLink() {
        actionDriver.click(loginPageLink);
    }
}
