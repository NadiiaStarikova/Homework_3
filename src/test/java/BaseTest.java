import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public static void main(String[] args) {
        WebDriver driver = initChromeDriver();
    }

    //инициализация браузера
    public static WebDriver initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
        return new ChromeDriver();
    }
    //закрытие браузера
    public static void quiteDriver (WebDriver driver) {driver.quit();}
}
