package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MoviePage extends BasePage{
    private static final Logger log = getLogger(MoviePage.class.getName());

    public MoviePage(WebDriver driver) {
        super(driver);
    }
    private final By genresBy = By.xpath("//span[@class='genres']");
    private final By nameBy = By.xpath("//section[@class='header poster']//a[contains(@href,'/movie/')]");
    private final By billedCastBy = By.xpath("//ol[@class= 'people scroller']/li/p/a");

    public String getGenresString(){
        log.info("Getting movie genres");
        return mapToElement(genresBy).getText();
    }

    public String getName() {
        log.info("Getting movie name");
        return mapToElement(nameBy).getText();
    }

    public ActorPage clickFirstCast() {
        log.info("First cast member in the casting bill selected");
        clickByJSExecutor(mapToElements(billedCastBy).get(0));
        return new ActorPage(driver);
    }
}
