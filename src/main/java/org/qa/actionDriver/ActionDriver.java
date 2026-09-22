package org.qa.actionDriver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ActionDriver {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final Logger loggr = BaseClass.loggr;

    // Constructeur de la classe
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicit"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        loggr.info("WebDriver Instance is created correctly and A Browser Opened and Maximized: {}", this.getClass().getSimpleName());
    }

    // 2 Methods of Tab Keyboard
    public void navigateWithTabAndPressEnter(By by) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            Actions actions = new Actions(driver);

            int maxAttempts = 15; // Limite de sécurité pour éviter la boucle infinie
            int attempts = 0;

            // Boucle avec garde-fou
            while (!driver.switchTo().activeElement().equals(element) && attempts < maxAttempts) {
                actions.sendKeys(Keys.TAB).perform();
                attempts++;
            }

            if (!driver.switchTo().activeElement().equals(element)) {
                throw new RuntimeException("Target element was not focused after " + maxAttempts + " TAB presses.");
            }

            // Appliquer la bordure verte sur le bouton cible avant de valider
            applyBorder(element, "green");
            ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "TAB to Element", "Focus reached [" + getElementDescription(by, element) + "], pressing ENTER");

            // Appuyer sur Entrée
            actions.sendKeys(Keys.ENTER).perform();
            loggr.info("Successfully navigated via TAB to [{}] and pressed ENTER", getElementDescription(by, element));

        } catch (Exception e) {
            applyBorder(element, "red");
            String description = getElementDescription(by, null);
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Tab & Enter Failed", "Unable to reach target [" + description + "] via TAB or press ENTER");
            loggr.error("Error during TAB navigation to element [{}] | Error: {}", description, e.getMessage());
            Assert.fail("Test failed: Could not navigate via TAB and press ENTER on [" + description + "]", e);
        }
    }

    // Method to enter text into input field
    public void tabToElementAndType(By targetBy, String text) {
        WebElement targetElement = null;
        try {
            targetElement = waitForElementToBeVisible(targetBy);
            Actions actions = new Actions(driver);

            int maxAttempts = 35; // Augmenté à 35 pour traverser le menu
            int attempts = 0;

            while (!driver.switchTo().activeElement().equals(targetElement) && attempts < maxAttempts) {
                actions.sendKeys(Keys.TAB).perform();
                attempts++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                } // Mini-pause pour stabiliser le focus
            }

            if (!driver.switchTo().activeElement().equals(targetElement)) {
                throw new RuntimeException("Target element was not focused after " + maxAttempts + " TAB presses.");
            }

            // Saisie du texte sur l'élément focalisé
            driver.switchTo().activeElement().sendKeys(text);
            applyBorder(targetElement, "green");

            String description = getElementDescription(targetBy, targetElement);
            loggr.info("Navigated via TAB to [{}] and entered text successfully", description);
            ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "TAB & Type", "Entered text in [" + description + "]");

        } catch (Exception e) {
            applyBorder(targetElement, "red");
            String description = getElementDescription(targetBy, null);
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "TAB & Type Failed", "Could not reach or type in [" + description + "]");
            loggr.error("Error during TAB navigation to [{}] | Error: {}", description, e.getMessage());
            Assert.fail("Test failed: Could not navigate via TAB and type in [" + description + "]", e);
        }
    }

    // Method to click on element
    public void click(By by) {
        WebElement element = waitForElementToBeClickable(by);
        try {
            // 1. Mémoriser cet élément
            ExtentManager.addHighlightedElement(by);
            // 2. Obtenir la description
            String description = getElementDescription(by, element);
            // 3. Appliquer la bordure verte AVANT le clic
            applyBorder(element, "green");
            // 4. PRENDRE LE SCREENSHOT MAINTENANT (Avant le changement de page / de DOM)
            ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Clicking on element...", "Element_" + description + "_clicked");
            // 5. Effectuer le Clic (peut recharger la page)
            element.click();
            loggr.info("Clicked on element ----------------> : [{}]", description);
            // 6. Retirer la bordure si la page n'a pas changé
            try {
                resetBorder(element);
            } catch (Exception ignored) {
                // Ignoré si la page a changé après le clic
            }
        } catch (Exception e) {
            String description = getElementDescription(by, element);
            if (element != null) {
                try {
                    applyBorder(element, "red");
                } catch (Exception ignored) {
                }
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Unable to click on this element !", description + "_unable_to_click");
            } else {
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Element not found !", description + "_element_not_found");
            }
            loggr.error("Element is not clickable [{}] | Error: {}", description, e.getMessage());
        }
    }

    // Method to enter text into input field
    public void enter(By by, String text) {
        WebElement element = waitForElementToBeVisible(by);
        try {
            // 1.  Mémoriser cet élèment pour la capture finale.
            ExtentManager.addHighlightedElement(by);
            element.clear();
            element.sendKeys(text);
            applyBorder(element, "green");
            ExtentManager.logStep("Value entered on ' " + getElementDescription(by) + " ' is ' " + text + " '.");
            loggr.info("Value entered on ' {} ' is [{}].", getElementDescription(by), text);
            resetBorder(element);
        } catch (Exception e) {
            applyBorder(element, "red");
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Unable to enter a value on this element!", getElementDescription(by) + "_unable_to_enter_value");
            loggr.error("Unable to Enter to Element [{}] | Error: {}", getElementDescription(by), e.getMessage());
        }
    }

    // Method to get text from input field
    public String getText(By by) {
        WebElement element = waitForElementToBeVisible(by);
        ;
        try {
            // 1.  Mémoriser cet élèment pour la capture finale.
            ExtentManager.addHighlightedElement(by);
            applyBorder(element, "green");
            resetBorder(element);
            return element.getText();
        } catch (Exception e) {
            applyBorder(element, "red");
            loggr.error("Unable to get Text From This Element [{}] | Error: {}", getElementDescription(by, null), e.getMessage());
            return null;
        }
    }

    // Method to compare Two Attribute
    public boolean compareByAttribute(By by, String attribute, String expectedValue) {
        WebElement element;
        try {
            element = waitForElementToBeVisible(by);
            String actualValue = element.getDomAttribute(attribute);

            if (expectedValue.equals(actualValue)) {
                // AJOUT:
                // Mémoriser cet élèment pour la capture finale.
                ExtentManager.addHighlightedElement(by);
                // 1. Appliquer d'abord le bordure verte
                applyBorder(element, "green");
                loggr.info("Actual Attribute [{}] value [{}] match to -----> Expected Attribute Value: [{}]", attribute, actualValue, expectedValue);
                ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Compare Text!", "Test Verified successfully: " + expectedValue + " equals " + actualValue);
                // IMPORTANT:
                // On supprime la bordure après le screenshot intermédiaire.
                resetBorder(element);
                return true;
            } else {
                // 1. Appliquer ensuite le bordure rouge
                applyBorder(element, "red");
                loggr.info("ERROR: Actual Attribute [{}] value [{}] does not match to -----> Expected Attribute Value: [{}]", attribute, actualValue, expectedValue);
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Text Comparison fails!", "Test failed: " + expectedValue + " not equals " + actualValue);
                // IMPORTANT:
                // On supprime la bordure après le screenshot intermédiaire.
                resetBorder(element);
                return false;
            }
        } catch (Exception e) {
            loggr.error("Unable to compare This Element By Attribute [{}] | Error: {}", getElementDescription(by), e.getMessage());
            return false;
        }
    }

    // Method to compare Two Text
    public boolean compareText(By by, String expectedText) {
        WebElement element;
        try {
            element = waitForElementToBeVisible(by);
            String actualText = element.getText();

            if (expectedText.equals(actualText)) {
                // 1.  Mémoriser cet élèment pour la capture finale.
                ExtentManager.addHighlightedElement(by);
                // 2. Appliquer d'abord le bordure verte
                applyBorder(element, "green");
                loggr.info("Actual Text: [{}] match to -----> Expected Text Value: [{}]", actualText, expectedText);
                ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Compare Text!", "Test Verified successfully: " + expectedText + " equals " + actualText);
                resetBorder(element);
                return true;
            } else {
                // 3. Appliquer ensuite le bordure rouge
                applyBorder(element, "red");
                loggr.info("ERROR: Actual Text : [{}] does not match to -----> Expected Text Value: [{}]", actualText, expectedText);
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Text Comparison fails!", "Test failed: " + expectedText + " not equals " + actualText);
                resetBorder(element);
                return false;
            }
        } catch (Exception e) {
            /*if (element != null) {
                applyBorder(element, "red");
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Echec comparison 2 Texts", "Error_" + getElementDescription(by) + "_not_be_compared");
                resetBorder(element);
            }*/
            loggr.error("Unable to compare This Element [{}] | Error: {}", getElementDescription(by, null), e.getMessage());
            return false;
        }
    }

    // Method to check if an element is displayed
    public boolean isDisplayed(By by) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            boolean isDisplayed = element.isDisplayed();
            String description = getElementDescription(by, element);

            if (isDisplayed) {
                applyBorder(element, "green");
                ExtentManager.logStep("Element is displayed: " + description);
                ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Element is displayed !", description + "_is_displayed");
                loggr.info("Element  [{}] ------> is displayed", description);
                return true;
            } else {
                applyBorder(element, "red");
                loggr.info("Element  [{}] ------> is not displayed", description);
                return false;
            }
        } catch (Exception e) {
            applyBorder(element, "red");
            String description = getElementDescription(by, null);
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Element is not displayed !", description + "_is_not_displayed");
            loggr.error("Element is not displayed: {} | Error: {}", description, e.getMessage());
            return false;
        }
    }

    // Method to wait and load page
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(webDriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            loggr.info("Page load successfully!");
        } catch (Exception e) {
            loggr.error("Page is not loaded within: {} seconds. Exception: {}", timeOutInSec, e.getMessage());
        }
    }

    // Wait for element to be clickable (Retourne désormais un WebElement)
    public WebElement waitForElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    // Wait for element to be visible (Retourne désormais un WebElement)
    public WebElement waitForElementToBeVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Method to get description of an element
    public String getElementDescription(By locator, WebElement element) {
        if (driver == null) return "driver is null";
        if (locator == null && element == null) return "locator is null";

        try {
            WebElement el = (element != null) ? element : driver.findElement(locator);

            String name = el.getDomAttribute("name");
            String id = el.getDomAttribute("id");
            String text = el.getText();
            String className = el.getDomAttribute("class");
            String placeHolder = el.getDomAttribute("placeholder");

            if (isNotEmpty(name)) return "Element with name ' " + name + " '";
            if (isNotEmpty(id)) return "Element with id ' " + id + " '";
            if (isNotEmpty(text)) return "Element with text ' " + truncate(text, 50) + " '";
            if (isNotEmpty(className)) return "Element with class ' " + className + " '";
            if (isNotEmpty(placeHolder)) return "Element with placeholder ' " + placeHolder + " '";
        } catch (Exception e) {
            return locator != null ? locator.toString() : "Element non localisable";
        }
        return locator != null ? locator.toString() : "Element non localisable";
    }

    public String getElementDescription(By locator) {
        return getElementDescription(locator, null);
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    private String truncate(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength) + "...";
    }

    // Method to return to find a list of web elements
    public List<WebElement> getElements(By by) {
        try {
            List<WebElement> elements = driver.findElements(by);
            if (!elements.isEmpty()) {
                // Register this locator for final screenshot
                ExtentManager.addHighlightedElement(by);
                int Index = 0;
                // Ajout: Border à tous les élèments
                for (WebElement element : elements) {
                    applyBorder(element, "green");
                    ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "one of List Find Elements", "web element's_" + getElementDescription(by) + "_founded_index_" + Index);
                    resetBorder(element);
                    Index++;
                }
            } else {
                loggr.warn("No Web Elements founded for: {}", getElementDescription(by));
            }
            loggr.info("Found [{}] web element(s)", elements.size());
            return elements;
        } catch (Exception e) {
            loggr.error("Error to get a list of web elements : {}", e.getMessage());
            return null;
        }
    }
    // ========================= Select Methods ================================

    public void selectByVisibleText(By by, String textOption) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            new Select(element).selectByVisibleText(textOption);
            applyBorder(element, "green");
            loggr.info("Selected dropdown text value : {}", textOption);
        } catch (Exception e) {
            applyBorder(element, "red");
            loggr.info("Unable to select a dropdown text value : {}", e.getMessage());
        }
    }

    public void selectByValue(By by, String valueOption) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            new Select(element).selectByValue(valueOption);
            applyBorder(element, "green");
            loggr.info("Selected dropdown value : {}", valueOption);
        } catch (Exception e) {
            applyBorder(element, "red");
            loggr.info("Unable to select a dropdown value : {}", e.getMessage());
        }
    }

    public void selectByIndex(By by, int index) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            new Select(element).selectByIndex(index);
            applyBorder(element, "green");
            loggr.info("Selected dropdown value by index : {}", index);
        } catch (Exception e) {
            applyBorder(element, "red");
            loggr.info("Unable to select a dropdown value by index : {}", e.getMessage());
        }
    }

    public List<String> getDroPDownOptions(By by) {
        List<String> options = new ArrayList<>();
        WebElement dropDownElement = null;
        try {
            dropDownElement = waitForElementToBeVisible(by);
            Select select = new Select(dropDownElement);
            for (WebElement option : select.getOptions()) {
                options.add(option.getText());
            }
            applyBorder(dropDownElement, "green");
            loggr.info("Retrieved options from dropdown : {}", options);
            return options;
        } catch (Exception e) {
            applyBorder(dropDownElement, "red");
            loggr.info("Unable to get dropdown options : {}", e.getMessage());
            return null;
        }
    }

    // ========================= JS Methods ================================
    public void scrollToElement(By by) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            applyBorder(element, "green");
            loggr.info("Scrolled to element --------> {} using JS", getElementDescription(by, element));
        } catch (Exception e) {
            applyBorder(element, "red");
            loggr.error("Unable to Scroll to Element to scroll [{}] | Error: {}", getElementDescription(by, null), e.getMessage());
        }
    }

    /*public void applyBorder(By by, String color) {
        WebElement element = driver.findElement(by);
        try {
            String script = "arguments[0].style.border = '3px solid " + color + "'";
            ((JavascriptExecutor) driver).executeScript(script, element);
            loggr.info("Border applied on element with color ------> [{}].", color);
        } catch (Exception e) {
            loggr.debug("Unable to apply border color on web element | Error: {}", e.getMessage());
        }
    }*/
    //
    public void applyBorder(WebElement element, String color) {
        try {
            String script = "arguments[0].style.border = '3px solid " + color + "'";
            ((JavascriptExecutor) driver).executeScript(script, element);

            loggr.info("Border applied on element with color ------> [{}].", color);

        } catch (Exception e) {
            loggr.debug("Unable to apply border color on web element | Error: {}", e.getMessage());
        }
    }

    /*public void resetBorder(By by) {
        WebElement element = driver.findElement(by);
        try {
            String script = "arguments[0].style.border = ''";
            ((JavascriptExecutor) driver).executeScript(script, element);
            loggr.info("Border reset on element !");

        } catch (Exception e) {
            loggr.debug("Unable to reset border color on web element {} | Error: {}", getElementDescription(by), e.getMessage());
        }
    }*/

    public void resetBorder(WebElement element) {
        try {
            String script = "arguments[0].style.border = ''";
            ((JavascriptExecutor) driver).executeScript(script, element);

            loggr.info("Border reset on element !");

        } catch (Exception e) {
            loggr.debug("Unable to reset border color on web element | Error: {}", e.getMessage());
        }
    }

    public void clickUsingJS(By by) {
        WebElement element = null;
        try {
            element = waitForElementToBeVisible(by);
            applyBorder(element, "green");
            String description = getElementDescription(by, element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            loggr.info("Clicked on element using JS : {}", description);
            ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Element clicked using JS", "Element_" + description + "_clicked_using_js");
        } catch (Exception e) {
            if (element != null) {
                applyBorder(element, "red");
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Unable to click using JS element!", getElementDescription(by, element) + "_unable_to_click");
            }
            loggr.error("Unable to click using JS element: {}", e.getMessage());
        }
    }

    // ========================= Window and Frame Methods ================================

    public void switchToFrame(By by) {
        try {
            WebElement frame = waitForElementToBeVisible(by);
            driver.switchTo().frame(frame);
            loggr.info("Switched to Frame : {}", getElementDescription(by, frame));
        } catch (Exception e) {
            loggr.info("Unable to Switch to Frame : {}", e.getMessage());
        }
    }

    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            loggr.info("Switched to Default content !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Default Content : {}", e.getMessage());
        }
    }

    // ========================= Alert Handling Methods ================================

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            loggr.info("Switched to alert and accepted !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Accept : {}", e.getMessage());
        }
    }

    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            loggr.info("Switched to alert and dismissed !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Dismissed : {}", e.getMessage());
        }
    }

    public String getAlertText() {
        try {
            String text = driver.switchTo().alert().getText();
            loggr.info("Switched to alert and retrieved text : {}", text);
            return text;
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Retrieve text : {}", e.getMessage());
            return "";
        }
    }

    // ========================= Navigation Methods ================================

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            loggr.info("Loading of page in the browser !");
        } catch (Exception e) {
            loggr.info("Unable to load a browser : {}", e.getMessage());
        }
    }

    public void openPage(String url) {
        try {
            driver.get(url);
            Thread.sleep(3);
            loggr.info("Opening of page with url {}: ", url);
        } catch (Exception e) {
            loggr.info("Unable to open a page with current url {} : ERROR: {}", url, e.getMessage());
        }
    }

    public String getCurrentURL() {
        try {
            loggr.info("Retrieved a current URL of Page : {}", driver.getCurrentUrl());
            return driver.getCurrentUrl();
        } catch (Exception e) {
            loggr.info("Unable to Retrieve a current URL of Page: {}", e.getMessage());
            return null;
        }
    }

    public void navigateBack() {
        try {
            driver.navigate().back();
            loggr.info("Clicked on Back Browser Button");
        } catch (Exception e) {
            loggr.info("Unable to navigate back in Page : {}", e.getMessage());
        }
    }

    public void maximizeWindow() {
        try {
            driver.manage().window().maximize();
            loggr.info("Maximized a Browser !");
        } catch (Exception e) {
            loggr.info("Unable to maximize a browser : {}", e.getMessage());
        }
    }

    public String getCurrentTitle() {
        try {
            loggr.info("Retrieved a current Title of Page: {}", driver.getTitle());
            return driver.getTitle();
        } catch (Exception e) {
            loggr.info("Unable to Retrieve a current Title of Page: {}", e.getMessage());
            return null;
        }
    }
}
