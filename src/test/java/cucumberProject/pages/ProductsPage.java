package cucumberProject.pages;

import org.openqa.selenium.By;

/**
 * BasePage - Clasa de bază pentru Page Object Model
 * Conține metode de bază pentru interacțiuni cu elementele paginii
 */
public class ProductsPage extends BasePage {
    private By productsTitle = By.xpath("//span[@class='title' and text()='Products']");
    private By addToCartProductsTitle = By.id("add-to-cart-sauce-labs-backpack");

    public void productsTitleIsDisplayed () {
        waitUntilElementIsDisplayed(productsTitle, 5);
    }

    public void selectProductTitle () {
    waitAndClick(addToCartProductsTitle, 10);}
}