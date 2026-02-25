package cucumberProject.pages;

import cucumberProject.utils.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - Clasa de bază pentru Page Object Model
 * Conține metode de bază pentru interacțiuni cu elementele paginii
 */
public class LoginPage extends BasePage {

    private By usernameInputXPath = By.xpath("//input[@id='user-name']");
    private By passwordInputXPath = By.xpath("//input[@id='password']");
    private By loginButtonXPath = By.xpath("//input[@id='login-button']");

    public void loginWithValidData (String username, String password) {
        waitAndType(usernameInputXPath, username, 5);
        waitAndType(passwordInputXPath, password, 5);
        waitAndClick(loginButtonXPath, 5);
    }
}