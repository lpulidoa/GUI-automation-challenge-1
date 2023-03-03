package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ActorPage extends BasePage{
    private static final Logger log = getLogger(ActorPage.class.getName());
    private final By actingCreditsBy = By.xpath("//div[@class= 'credits_list']//a[contains(@href,'/movie/')]");
    public ActorPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getActingCredits() {
        List<String> credits = new ArrayList<>();
        List<WebElement> creditsBy = mapToElements(actingCreditsBy);

        for (WebElement webElement : creditsBy) {
            credits.add(webElement.getText());
        }
        log.info("The acting credits are: " + credits);
        return credits;
    }
}
