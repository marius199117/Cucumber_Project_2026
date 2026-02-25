package cucumberProject.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;

/**
 * BrowserHelper - Clasa de help cu operații generale pe browser
 * Conține metode helper pentru navigare, alerts, frames, etc.
 */
public class BrowserHelper {

    /**
     * Obține driverul curent
     */
    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    /**
     * Navighează la un URL
     */
    public static void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    /**
     * Așteptă (sleep) pentru o perioadă de timp în secunde
     */
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Obține titlul paginii curente
     */
    public static String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    /**
     * Obține URL-ul curent
     */
    public static String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    /**
     * Reîmprospătează pagina
     */
    public static void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
    }

    /**
     * Înapoi în browser (back button)
     */
    public static void goBack() {
        DriverManager.getDriver().navigate().back();
    }

    /**
     * Înainte în browser (forward button)
     */
    public static void goForward() {
        DriverManager.getDriver().navigate().forward();
    }

    /**
     * Obține handle-ul curent al ferestrei
     */
    public static String getCurrentWindowHandle() {
        return DriverManager.getDriver().getWindowHandle();
    }

    /**
     * Schimbă fereastra după handle
     */
    public static void switchToWindow(String windowHandle) {
        DriverManager.getDriver().switchTo().window(windowHandle);
    }

    /**
     * Schimbă la un frame după ID
     */
    public static void switchToFrame(String frameId) {
        DriverManager.getDriver().switchTo().frame(frameId);
    }

    /**
     * Revine din frame la default content
     */
    public static void switchToDefaultContent() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    /**
     * Acceptă alert-ul curent
     */
    public static void acceptAlert() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }

    /**
     * Anulează alert-ul curent (dismiss)
     */
    public static void dismissAlert() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.dismiss();
    }

    /**
     * Obține textul din alert-ul curent
     */
    public static String getAlertText() {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        return alert.getText();
    }

    /**
     * Introduceți text într-un alert și acceptati
     */
    public static void sendTextToAlert(String text) {
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    /**
     * Obține numărul de ferestre deschise
     */
    public static int getWindowCount() {
        return DriverManager.getDriver().getWindowHandles().size();
    }

    /**
     * Schimbă la fereastra următoare
     */
    public static void switchToNextWindow() {
        java.util.Set<String> handles = DriverManager.getDriver().getWindowHandles();
        String currentHandle = DriverManager.getDriver().getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                DriverManager.getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    /**
     * Maximizează fereastra
     */
    public static void maximizeWindow() {
        DriverManager.getDriver().manage().window().maximize();
    }


    /**
     * Setează dimensiunea ferestrei
     */
    public static void setWindowSize(int width, int height) {
        org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(width, height);
        DriverManager.getDriver().manage().window().setSize(dimension);
    }
}