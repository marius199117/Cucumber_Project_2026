package cucumberProject.pages;

import cucumberProject.utils.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - Clasa de bază pentru Page Object Model
 * Conține metode de bază pentru interacțiuni cu elementele paginii
 */
public class BasePage {

    /**
     * Asteapta și face click pe un element
     */
    public void waitAndClick(By element, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Asteapta și introduce text într-un câmp
     */
    /**
     * Așteaptă ca elementul să fie clickabil și scrie text în el
     */
    public void waitAndType(By locator, String textToType, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement textBox = waiter.until(ExpectedConditions.elementToBeClickable(locator));
        Assert.assertNotNull(textBox);
        textBox.sendKeys(textToType);
    }

    /**
     * Asteapta și verifică că un element este afișat
     */
    public boolean waitUntilElementIsDisplayed(By xpath, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.presenceOfElementLocated(xpath));
        return driver.findElement(xpath).isDisplayed();
    }

    /**
     * Găsește un element după XPath
     */
    /**
     * Găsește un element după locator (XPath, CSS etc.)
     */
    public WebElement findElement(By locator) {
        WebDriver driver = DriverManager.getDriver();
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Obține URL-ul curent
     */
    public String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    /**
     * Navighează la o pagină
     */
    public void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    /**
     * Asteapta ca un element să devină inactiv
     */
    public void waitUntilElementIsInvisible(By xpath, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    /**
     * Obține textul unui element
     */
    public String getText(By locator) {
        WebElement element = findElement(locator);
        return element != null ? element.getText() : null;
    }
    /**
     * Verifică dacă un element este prezent
     */
    public boolean isElementPresent(By locator) {
        return findElement(locator) != null;
    }
}