package StepDefinitions;

import java.util.concurrent.TimeUnit;

import SetUp.SetUp;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import HomePAge;
import pageObjects.ProductDetailPAge;
import pageObjects.ResultsPage;
import pageObjects.LoginPage;

public class AmazonSearching {

    WebDriver driver;
    SetUp setup;
    LoginPage loginpage;
    HomePAge homepage;
    ResultsPage resultspage;
    ProductDetailPAge productpage;

    String firstPrice;
    String firstProduct;
    String secondProduct;
    String cartCount;

    public AmazonSeraching() {
        driver = Hooks.driver;
        setup = new SetUp();
        loginpage = new LoginPage(driver);
        homepage = new HomePAge(driver);
        resultspage = new ResultsPage(driver);
        productpage = new ProductDetailPAge(driver);

    }

    @Given("^I go to amazon$")
    public void i_go_to_amazon()  {
        driver.get(setup.getUrl());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("^I login with my \"(.*?)\" and \"(.*?)\"$")
    public void i_login_with_my_and(String email, String password) {

        homepage.clickLogin();
        loginpage.loginSubmit(email, password);

    }

    @Then("^I Search for product: \"([^\"]*)\"$")
    public void i_Search_for_product(String firstProduct)  {

        firstProduct = setup.getFirstProduct();

        homepage.searchProduct(firstProduct);
    }

    @Then("^I Select first product and validate first price vs detail price$")
    public void i_Select_first_product_and_validate_first_price_vs_detail_price()  {

        firstPrice = resultspage.getFirstPrice();

        resultspage.clickFirstProduct();

        String detailPrice = productpage.getPrice();

        assert.assertTrue(detailPrice.contains(firstPrice));
    }

    @When("^I click on Add to Cart$")
    public void i_click_on_Add_to_Cart()  {
        productpage.addToCart();
    }

    @And("^Validate first price vs current price And validate Shop car has 1 item$")
    public void validate_first_price_vs_current_price_And_validate_Shop_car_has_item()  {

        String cartPrice = homepage.getCartPrice();
        cartCount = homepage.getCartCount();
        int cartCount = Integer.parseInt(cartCount);

        softAssertion.assertTrue(cartPrice.contains(firstPrice));
        softAssertion.assertEquals(cartCount, 1);
    }

    @Then("^I Search for second product: \"([^\"]*)\"$")
    public void i_Search_for_second_product(String secondProduct) {

        secondProduct = setup.getSecondProduct();

        homepage.searchProduct(secondProduct);
    }

    @And("^Select first product$")
    public void select_first_product() throws Throwable {
        resultspage.clickFirstProduct();
    }

    @Then("^I add second product to the cart And verify cart has two items$")
    public void i_add_second_product_to_the_cart_And_verify_cart_has_two_items() {

        productpage.addToCart();
        cartCount = homepage.getCartCount();
        int cartCountItems = Integer.parseInt(cartCount);
        //Asert here!!!
        assert.asserEquals(cartCountItems,cartCount);

    }

}
