package cucumberProject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * BrowserFactory - Factory Pattern pentru crearea și configurarea WebDriver-elor
 * Suportă Chrome, Firefox, Edge, Safari
 */
public class BrowserFactory {

    /**
     * Enum pentru tipurile de browsere suportate
     */
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }

    /**
     * Creează și returnează WebDriver pentru browserul selectat
     * @param browserType Tipul de browser (CHROME, FIREFOX, EDGE, SAFARI)
     * @return WebDriver configurat
     */
    public static WebDriver createBrowser(BrowserType browserType) {
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                driver = createChromeDriver();
                System.out.println("Chrome Browser initialized");
                break;

            case FIREFOX:
                driver = createFirefoxDriver();
                System.out.println("Firefox Browser initialized");
                break;

            case EDGE:
                driver = createEdgeDriver();
                System.out.println("Edge Browser initialized");
                break;

            case SAFARI:
                driver = createSafariDriver();
                System.out.println("Safari Browser initialized");
                break;

            default:
                throw new RuntimeException("Unsupported browser type: " + browserType);
        }

        // Configurări generale pentru toți browserii
        driver.manage().window().maximize();
        return driver;
    }

    // Helper: check headless flag from system properties (default true)
    private static boolean isHeadless() {
        String prop = System.getProperty("headless", "true");
        return Boolean.parseBoolean(prop);
    }

    /**
     * Creează ChromeDriver cu opțiuni configurate
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        // Dezactivare password manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-popup-blocking");

        if (isHeadless()) {
            chromeOptions.addArguments("--headedless");
        }

        chromeOptions.setAcceptInsecureCerts(true);

        return new ChromeDriver(chromeOptions);
    }

    /**
     * Creează FirefoxDriver cu opțiuni configurate
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // Acceptă certificatele nesigure
        firefoxOptions.setAcceptInsecureCerts(true);

        // Headless mode (comandabil via -Dheadless=false)
        if (isHeadless()) {
            firefoxOptions.addArguments("--headless");
        }

        // Alte opțiuni
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");

        return new FirefoxDriver(firefoxOptions);
    }

    /**
     * Creează EdgeDriver cu opțiuni configurate
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions edgeOptions = new EdgeOptions();
        // Acceptă certificatele nesigure
        edgeOptions.setCapability("ms:loggingPrefs", "DEBUG");
        // Putem seta alte proprietăți direct

        return new EdgeDriver(edgeOptions);
    }

    /**
     * Creează SafariDriver
     * Notă: Safari pe macOS se lansează fără headless mode
     */
    private static WebDriver createSafariDriver() {
        // Safari nu necesită WebDriverManager setup
        return new SafariDriver();
    }

    /**
     * Obține browserul din variabila de mediu (opțional)
     * @return BrowserType selectat sau CHROME ca default
     */
    public static BrowserType getBrowserFromEnvironment() {
        String browser = System.getProperty("browser", "CHROME").toUpperCase();

        try {
            return BrowserType.valueOf(browser);
        } catch (IllegalArgumentException e) {
            System.out.println("Browser not recognized: " + browser + ". Using CHROME as default.");
            return BrowserType.CHROME;
        }
    }

    /**
     * Creează browser din variabila de mediu
     * Utilizare: mvn test -Dbrowser=FIREFOX
     */
    public static WebDriver createBrowserFromEnvironment() {
        return createBrowser(getBrowserFromEnvironment());
    }
}
