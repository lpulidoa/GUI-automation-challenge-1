package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.apache.logging.log4j.LogManager.getLogger;

public class SearchResultsPage extends BasePage{
    private static final Logger log = getLogger(MainPage.class.getName());
    public SearchResultsPage(WebDriver driver){
        super(driver);
    }
    private final By resultsTitles = By.xpath("//div[@class='title']//h2");

    public String getFirstResultTitle() {
        return mapToElements(resultsTitles).get(0).getText();
    }
}
