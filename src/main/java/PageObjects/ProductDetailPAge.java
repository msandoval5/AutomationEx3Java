package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPAge {
    WebDriver driver;
    WebDriverWait wait;
    ResultsPage resultsPage;
    @FindBy(
            id = "price_inside_buybox"
    )
    WebElement productPrice;
    @FindBy(
            id = "add-to-cart-button"
    )
    WebElement addToCartButton;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 10L);
        this.resultsPage = new ResultsPage(driver);
    }

    public String getPrice() {
        return this.productPrice.getText();
    }

    public void addToCart() {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(this.addToCartButton));
            this.addToCartButton.click();
        } catch (Exception e) {
            System.err.println("Failed " + e.getMessage());
            this.driver.quit();
        }

    }
}
