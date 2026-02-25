package cucumberProject.utils;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager - Gestionează WebDriver cu ThreadLocal pentru execuție paralelă
 * Asigură izolarea driverului pe fiecare thread
 * Folosește BrowserFactory pentru crearea browserului
 */
public class DriverManager {

    // ThreadLocal pentru a stoca driverul unic per thread
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // Default browser type
    private static BrowserFactory.BrowserType currentBrowserType = BrowserFactory.BrowserType.CHROME;

    /**
     * Setează tipul de browser înainte de inițializare
     * @param browserType Tipul de browser de utilizat
     */
    public static void setBrowserType(BrowserFactory.BrowserType browserType) {
        currentBrowserType = browserType;
    }

    /**
     * Inițializează WebDriver pentru thread-ul curent folosind BrowserFactory
     */
    public static void initializeDriver() {
        // Verifică dacă driverul este deja inițializat pentru acest thread
        if (threadLocalDriver.get() != null) {
            System.out.println("Driver already initialized for thread: " + Thread.currentThread().getName());
            return;
        }

        // Creează driverul folosind BrowserFactory
        WebDriver driver = BrowserFactory.createBrowser(currentBrowserType);

        // Stochează driverul în ThreadLocal
        threadLocalDriver.set(driver);
        System.out.println("Browser initialized for thread: " + Thread.currentThread().getName());
    }

    /**
     * Inițializează WebDriver cu un tip de browser specific
     * @param browserType Tipul de browser de utilizat
     */
    public static void initializeDriver(BrowserFactory.BrowserType browserType) {
        setBrowserType(browserType);
        initializeDriver();
    }

    /**
     * Inițializează WebDriver din variabila de mediu
     * Utilizare: mvn test -Dbrowser=FIREFOX
     */
    public static void initializeDriverFromEnvironment() {
        setBrowserType(BrowserFactory.getBrowserFromEnvironment());
        initializeDriver();
    }

    /**
     * Obține driverul pentru thread-ul curent
     */
    public static WebDriver getDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver == null) {
            throw new RuntimeException("WebDriver not initialized for thread: " + Thread.currentThread().getName());
        }
        return driver;
    }

    /**
     * Închide și eliminate driverul pentru thread-ul curent
     */
    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            try {
                driver.close();
                driver.quit();
                System.out.println("Browser closed for thread: " + Thread.currentThread().getName());
            } catch (Exception e) {
                System.out.println("Error closing browser: " + e.getMessage());
            } finally {
                threadLocalDriver.remove();
            }
        }
    }

    /**
     * Verifică dacă driverul este inițializat pentru thread-ul curent
     */
    public static boolean isDriverInitialized() {
        return threadLocalDriver.get() != null;
    }

    /**
     * Obține tipul de browser curent
     */
    public static BrowserFactory.BrowserType getCurrentBrowserType() {
        return currentBrowserType;
    }
}