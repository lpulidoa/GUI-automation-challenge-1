package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {

    private WebDriverFactory() {}

    public static WebDriver getDriver(BrowserType browser){
        WebDriver driver = null;
        if (browser.name().equalsIgnoreCase("CHROME")){
            driver = new ChromeDriver();
        } else if (browser.name().equalsIgnoreCase("EDGE")) {
            driver = new EdgeDriver();
        }
        return driver;
    }
}
