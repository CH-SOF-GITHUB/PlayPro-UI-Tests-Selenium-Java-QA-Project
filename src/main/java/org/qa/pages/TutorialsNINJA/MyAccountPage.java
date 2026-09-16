package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class MyAccountPage {
    private ActionDriver actionDriver;


    // Locate Web Element of Success Message
    private final By MyAccountTitle = By.xpath("//div[@id='content']/h2");

    // Add Constructor of class page with Singleton Design Patter
    public MyAccountPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Check of a success message displays when login pass
    public boolean compareMyAccountTitle(String ExpectedSuccessMsg) {
        return actionDriver.compareText(MyAccountTitle, ExpectedSuccessMsg);
    }

}
