package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePAge {
    WebDriver driver;
    WebDriverWait wait;
    Data data;

    @FindBy(id = "nav-link-accountList")
    WebElement loginLink;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(xpath = "//input[@type='submit' and @value='Ir']")
    WebElement searchButton;

    @FindBy(xpath = "//span[@class='a-size-medium a-color-price hlb-price a-inline-block a-text-bold']")
    WebElement cartPrice;

    @FindBy(id = "nav-cart-count")
    WebElement cartItems;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        data = new Data();
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginLink));
            loginLink.click();
        } catch (Exception e) {
            System.err.println("Failed: " + e.getMessage());
            driver.quit();
        }

    }

    public void searchProduct(String Product) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchInput));

            searchInput.sendKeys(Product);

            searchButton.click();

        } catch (Exception e) {
            System.err.println("Failed:  " + e.getMessage());
            driver.quit();
        }
    }

    public String getCartPrice() {
        return cartPrice.getText();
    }

    public String getCartCount() {

        return cartItems.getText();
    }

}
