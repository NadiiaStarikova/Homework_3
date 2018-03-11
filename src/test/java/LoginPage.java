import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.id("email");
    private By passwdField = By.id("passwd");
    private By loginButton = By.className("ladda-label");
    private String email = "webinar.test@gmail.com";
    private String password = "Xcg7299bnSmMuRLp9ITw";

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
    }

    public void fillEmailField() {
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPasswdField() {
        driver.findElement(passwdField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
