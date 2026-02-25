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
    public void waitAndClick(String xpath, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * Asteapta și introduce text într-un câmp
     */
    public void waitAndType(String xpathForStatedTextBox, String stringToType, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForStatedTextBox)));
        WebElement statedTextBox = findElementByXPath(xpathForStatedTextBox);
        Assert.assertNotNull(statedTextBox);
        statedTextBox.sendKeys(stringToType);
    }

    /**
     * Asteapta și verifică că un element este afișat
     */
    public boolean waitUntilElementIsDisplayed(String xpath, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath)).isDisplayed();
    }

    /**
     * Găsește un element după XPath
     */
    public WebElement findElementByXPath(String xPathToSearchFor) {
        WebDriver driver = DriverManager.getDriver();
        try {
            return driver.findElement(By.xpath(xPathToSearchFor));
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
    public void waitUntilElementIsInvisible(String xpath, int seconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Obține textul unui element
     */
    public String getText(String xpath) {
        return findElementByXPath(xpath).getText();
    }

    /**
     * Verifică dacă un element este prezent
     */
    public boolean isElementPresent(String xpath) {
        return findElementByXPath(xpath) != null;
    }
}