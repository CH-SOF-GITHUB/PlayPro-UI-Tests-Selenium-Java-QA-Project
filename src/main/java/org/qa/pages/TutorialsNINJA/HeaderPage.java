package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class HeaderPage {

    private ActionDriver actionDriver;

    public HeaderPage() {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Locate web elements of header options
    private final By desktopsOption = By.xpath("//ul[@class='nav navbar-nav']//li[@class='dropdown']//a[text()='Desktops']");
    private final By showAllDesktopsOption = By.linkText("Show AllDesktops");

    private final By laptopAndNoteBooksOption = By.xpath("//ul[@class='nav navbar-nav']//li[@class='dropdown']//a[text()='Laptops & Notebooks']");
    private final By Windows_0 = By.linkText("Windows (0)");

    private final By componentsOption = By.xpath("//a[normalize-space()='Components']");
    private final By Mice_and_Trackballs_0 = By.xpath("//a[normalize-space()='Mice and Trackballs (0)']");

    private final By tabletsOption = By.xpath("//a[normalize-space()='Tablets']");


    private final By softwareOption = By.xpath("//a[normalize-space()='Software']");
    private final By phonesAndPDAsOption = By.xpath("//a[normalize-space()='Phones & PDAs']");
    private final By camerasOption = By.xpath("//a[normalize-space()='Cameras']");

    private final By mp3PlayersOption = By.xpath("//a[normalize-space()='MP3 Players']");
    private final By test_11_0 = By.xpath("//a[normalize-space()='test 11 (0)']");

    // Method to clock on Desktops option in header
    public void clickOnDesktopsOption() {
        actionDriver.click(desktopsOption);
        actionDriver.click(showAllDesktopsOption);
    }

    public void clickOnLaptopAndNoteBooksOption() {
        actionDriver.click(laptopAndNoteBooksOption);
        actionDriver.click(Windows_0);
    }

    public void clickOnComponentsOption() {
        actionDriver.click(componentsOption);
        actionDriver.click(Mice_and_Trackballs_0);
    }

    public void clickOnTabletsOption() {
        actionDriver.click(tabletsOption);
    }

    public void clickOnSoftwareOption() {
        actionDriver.click(softwareOption);
    }

    public void clickOnPhonesAndPDAsOption() {
        actionDriver.click(phonesAndPDAsOption);
    }

    public void clickOnCamerasOption() {
        actionDriver.click(camerasOption);
    }

    public void clickOnMp3PlayersOption() {
        actionDriver.click(mp3PlayersOption);
        actionDriver.click(test_11_0);
    }
}
