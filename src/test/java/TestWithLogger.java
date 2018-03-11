import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestWithLogger extends BaseTest {

    public static void main(String[] args) {

        //Вход в Админ Панель
        EventFiringWebDriver driver = getConfiguredDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.fillEmailField();
        loginPage.fillPasswdField();
        loginPage.clickLoginButton();

        //Явное ожидание входа в Админ Панель
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("process-icon-toggle-off")));

        //Выбор пункта меню Каталог -> категории
        WebElement catalogMenuBtn = driver.findElement(By.id("subtab-AdminCatalog"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catalogMenuBtn).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtab-AdminCatalog")));
        catalogMenuBtn.findElements(By.cssSelector("li")).get(1).click();

        //Явное ожидание загрузки страницы управления категориями и появления кнопки «Добавить категорию»
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-category-new_category")));

        //Нажатие кнопки «Добавить категорию» для перехода к созданию новой категории
        WebElement addCategoryBtn = driver.findElement(By.id("page-header-desc-category-new_category"));
        addCategoryBtn.click();

        //Явное ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name_1")));

        //Ввод названия новой категории и сохранение изменения
        WebElement addCategoryNameField = driver.findElement(By.id("name_1"));
        addCategoryNameField.sendKeys("Children");
        WebElement saveCategoryBtn = driver.findElement(By.id("category_form_submit_btn"));
        saveCategoryBtn.click();

        //Проверка появления сообщения об успешном создании категории
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bootstrap")));
        assertTrue("No new category", driver.findElements(By.className("bootstrap")).size() > 0);
        System.out.println ("New category has been created successfully");

        //Явное ожидание загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("categoryFilter_name")));

        //Фильтрация таблицы категорий по имени
        WebElement nameFilterField = driver.findElement(By.name("categoryFilter_name"));
        nameFilterField.sendKeys("Children");
        nameFilterField.submit();

        //Явное ожидание появления записи созданной категории
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pointer")));

        //Проверка наличия созданной категории в таблице категорий после её фильтрации
        WebElement tableCategory = driver.findElement(By.id("table-category"));
        String actualCategoryName = tableCategory.findElements(By.cssSelector("td")).get(2).getText();
        assertEquals("Wrong category name", "Children", actualCategoryName);
        System.out.println("Expected category name: Children." + " Actual category name: "  + actualCategoryName + ".");

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
