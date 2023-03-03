package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;

    private static final Logger log = getLogger(BasePage.class.getName());

    public BasePage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement mapToElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> mapToElements(By locator){
        return driver.findElements(locator);
    }

    public void clickByJSExecutor(WebElement element) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void waitForElementPresence(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        log.info("Waiting for an element to be present");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));

    }
}
