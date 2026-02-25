package cucumberProject;

import cucumberProject.pages.BasePage;
import cucumberProject.pages.LoginPage;
import cucumberProject.utils.BrowserHelper;
import cucumberProject.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

/**
 * BaseTest - Clasa de bază pentru configurarea testelor Cucumber
 * Conține @Before/@After hooks și extinde BasePage
 */
public class BaseTest extends BasePage {

    protected WebDriver driver;

    /**
     * @Before Hook - Rulează înainte de fiecare Scenario Cucumber
     * Inițializează driverul
     */
    @Before
    public void setUp() {
        DriverManager.initializeDriver();
        this.driver = DriverManager.getDriver();
        BrowserHelper.navigateTo("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidData("standard_user", "secret_sauce");
        SoftAssert softAssert = new SoftAssert();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = BrowserHelper.getCurrentUrl();
        softAssert.assertEquals(actualURL, expectedURL, "Login failed: URL mismatch");
        softAssert.assertAll();
    }

    /**
     * @After Hook - Rulează după fiecare Scenario Cucumber
     * Închide și curață driverul
     */
    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}