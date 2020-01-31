package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    Data data;

    @FindBy(id = "ap_email")
    WebElement email;

    @FindBy(id = "ap_password")
    WebElement password;

    @FindBy(id = "signInSubmit")
    WebElement submitButton;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        data = new Data();
    }

    public void setEmail(String Email) {
        try {
            wait.until(ExpectedConditions.visibilityOf(email));
            Email = data.getEmail();
            email.sendKeys(Email);
        } catch (Exception e) {
            System.err.println("Failed " + e.getMessage());
            driver.quit();
        }
    }

    public void setPassword(String Password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(password));
            Password = data.getPassword();
            password.sendKeys(Password);
        } catch (Exception e) {
            System.err.println("Failed " + e.getMessage());
            driver.quit();
        }
    }

    public void submitLogin(String Email, String Password) {
        this.setEmail(Email);
        this.setPassword(Password);
        this.submitButton.Click();
    }

}
