import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventHandler implements WebDriverEventListener {

    public void beforeAlertAccept(WebDriver webDriver){
    }
    public void afterAlertAccept(WebDriver webDriver){
    }
    public void afterAlertDismiss(WebDriver webDriver){
    }
    public void beforeAlertDismiss(WebDriver webDriver){
    }
    public void beforeNavigateTo(String s, WebDriver webDriver){
    }
    public void afterNavigateTo(String s, WebDriver webDriver){ System.out.println("Open url: " + s);}
    public void beforeNavigateBack(WebDriver webDriver){
    }
    public void afterNavigateBack(WebDriver webDriver){
    }
    public void beforeNavigateForward(WebDriver webDriver){
    }
    public void afterNavigateForward(WebDriver webDriver){
    }
    public void beforeNavigateRefresh(WebDriver webDriver){
    }
    public void afterNavigateRefresh(WebDriver webDriver){
    }
    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Exception occured: " + error);
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Click on element: " + webElement.getTagName() + " " + webElement.getAttribute("name"));
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        System.out.println("Element successfully clicked");
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences){
        System.out.println("Fill input " + webElement.getAttribute("id") + " with value " + charSequences.toString());
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        System.out.println("Value successfully changed");
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find Element By : " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Found Element By : " + by.toString());
    }

    public void beforeScript(String script, WebDriver driver) {
    }

    public void afterScript(String script, WebDriver driver) {
    }
}
