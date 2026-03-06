package cucumberProject.steps;

import cucumberProject.pages.BasePage;
import cucumberProject.pages.LoginPage;
import cucumberProject.pages.ProductsPage;
import cucumberProject.utils.BrowserHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;


public class ProductsPageSteps {
    ProductsPage productsPage = new ProductsPage();

    @Then("I Verify That Products Title Is Displayed")
    public void i_Verify_That_Is_Displayed() {
        productsPage.productsTitleIsDisplayed();
    }

    @When("I Click On Sauce Labs Add To cart Element")
    public void i_Click_On_Element() {
        productsPage.selectProductTitle();
    }
}