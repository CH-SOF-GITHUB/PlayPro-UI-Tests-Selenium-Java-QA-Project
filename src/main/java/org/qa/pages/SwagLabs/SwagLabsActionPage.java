package org.qa.pages.SwagLabs;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Objects;

public class SwagLabsActionPage {

    // Page Object Web Driver
    private final WebDriver driver;

    public SwagLabsActionPage(WebDriver driver) {
        this.driver = driver;
    }

    // List Methods
    public List<WebElement> getList(By by) {
        try {
            return driver.findElements(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    /* Browser Methods */
    public void reloadPage() {
        try {
            driver.navigate().refresh();
        } catch (RuntimeException e) {
            throw new RuntimeException("Page not loaded correctly : " + e.getMessage());
        }
    }

    public void backPage() {
        try {
            driver.navigate().back();
        } catch (RuntimeException e) {
            throw new RuntimeException("Page not back correctly : " + e.getMessage());
        }
    }

    public void forwardPage() {
        try {
            driver.navigate().forward();
        } catch (RuntimeException e) {
            throw new RuntimeException("Page not forward correctly : " + e.getMessage());
        }
    }

    // Action Methods
    public void clickOnElement(Object locatorOrElement) {
        try {
            WebElement element;
            // If you use locator By
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                // If you use web element
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            element.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    public void enter(By by, String value) {
        try {
            driver.findElement(by).click();
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    // URL , Title methods
    public boolean isUrlOK(String base_url, String text) {
        try {
            String expectedURL = base_url + text;
            String currentURL = driver.getCurrentUrl();
            return base_url != null && currentURL != null && currentURL.equals(expectedURL);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to retrieve URL of current Page", e);
        }
    }

    public boolean isContain(String Text) {
        try {
            String currentURL = driver.getCurrentUrl();
            return currentURL != null && currentURL.contains(Text);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to retrieve URL of current Page", e);
        }
    }

    public boolean isTitle(String expectedTitle) {
        try {
            String currentTITLE = driver.getTitle();
            return currentTITLE != null && currentTITLE.equals(expectedTitle);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to retrieve URL of current Page", e);
        }
    }

    //
    public boolean isElementDisplayed(Object locatorOrElement) {
        try {
            WebElement element;
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No such element : " + e.getMessage());
        }
    }

    public boolean isElementNotVisible(Object locatorOrElement) {
        long startTime = System.currentTimeMillis();
        long timeoutMs = 10000; // Limite maximale de 10 secondes (ajustez si besoin à 1000ms)

        while ((System.currentTimeMillis() - startTime) < timeoutMs) {
            try {
                WebElement element;
                if (locatorOrElement instanceof By) {
                    element = driver.findElement((By) locatorOrElement);
                } else if (locatorOrElement instanceof WebElement) {
                    element = (WebElement) locatorOrElement;
                } else {
                    throw new IllegalArgumentException("Parameter should be of type By or WebElement");
                }

                // Si l'élément est trouvé mais n'est plus affiché
                if (!element.isDisplayed()) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                // L'élément a totalement disparu du DOM : il n'est plus visible !
                return true;
            }

            // Petite pause de 100ms entre chaque vérification pour ne pas surcharger le processeur
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Si le temps imparti est dépassé et que le spinner est toujours là
        return false;
    }
    /*
    public boolean isElementNotVisible(Object locatorOrElement) {
        try {
            WebElement element;
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            return !element.isDisplayed() && Thread.sleep();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            // throw new NoSuchElementException("No such element : " + e.getMessage());
            return true;
        }
    }*/

    public String getElementText(Object locatorOrElement) {
        try {
            WebElement element;
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            return element.getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }

    public boolean compareAttribute(By by, String attribute, String expectedValue) {
        try {
            System.out.println("Attribute - " + attribute + " : " + driver.findElement(by).getDomAttribute(attribute));
            return Objects.equals(driver.findElement(by).getDomAttribute(attribute), expectedValue);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }

    public boolean compareElement(Object locatorOrElement, Object expectedValue) {
        try {
            WebElement element;
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            //
            if (!(expectedValue instanceof Integer || expectedValue instanceof String)) {
                throw new IllegalArgumentException("Parameter should be of type Integer or String");
            }
            return element.getText().equals(String.valueOf(expectedValue));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot compare this element:  " + e.getMessage());
        }
    }
}
