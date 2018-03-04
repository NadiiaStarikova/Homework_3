import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestWithLogger {

    public static void main(String[] args) {

        //Вход в Админ Панель
        EventFiringWebDriver driver = getConfiguredDriver();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys("webinar.test@gmail.com");
        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys("Xcg7299bnSmMuRLp9ITw");
        WebElement button = driver.findElement(By.className("ladda-label"));
        button.click();

        //Явное ожидание входа в Админ Панель
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=AdminDashboard&token=f57b9e0913a4757c6ba02314cf473896"));

        //Выбор пункта меню Каталог -> категории
        WebElement adminCatalog = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions actions = new Actions(driver);
        actions.moveToElement(adminCatalog).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCatalog")));
        adminCatalog.findElements(By.cssSelector("li")).get(1).click();

        //Явное ожидание загрузки страницы управления категориями и появления кнопки «Добавить категорию»
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-category-new_category")));

        //Нажатие кнопки «Добавить категорию» для перехода к созданию новой категории
        WebElement addCategory = driver.findElement(By.id("page-header-desc-category-new_category"));
        addCategory.click();

        //Явное ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name_1")));

        //Ввод названия новой категории и сохранение изменения
        WebElement addNameCategory = driver.findElement(By.id("name_1"));
        addNameCategory.sendKeys("Children");
        WebElement saveButton = driver.findElement(By.id("category_form_submit_btn"));
        saveButton.click();

        //Явное ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("categoryFilter_name")));

        //Фильтрация таблицы категорий по имени
        WebElement filterByName = driver.findElement(By.name("categoryFilter_name"));
        filterByName.sendKeys("Children");
        filterByName.submit();

        //Явное ожидание появления записи созданной категории
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pointer")));

        //Закрытие браузера
        driver.quit();
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new EventHandler());
        return driver;
    }
}
