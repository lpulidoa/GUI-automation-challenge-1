package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MainPage extends BasePage{
    private static final Logger log = getLogger(MainPage.class.getName());
    public MainPage(WebDriver driver){
        super(driver);
    }
    private final By loginButtonBy = By.xpath("//ul[@class='primary']//a[@href='/login']");
    private final By searchBarBy = By.xpath("//input[@id='inner_search_v4']");
    private final By searchButtonBy = By.xpath("//input[@value='Search']");
    private final By movieDropdownBy = By.xpath("//a[@class='no_click k-link k-menu-link'][@href='/movie']");
    private final By topRatedButtonBy = By.xpath("//a[@href='/movie/top-rated']");
    private final By moviesTrendingBy = By.xpath("//div[@id= 'trending_scroller']//h2//a[contains(@href,'/movie/')]");


    public MoviePage clickTrendingMovie() {

        waitForElementPresence(moviesTrendingBy);

        log.info("Click on ANY movie");

        List<WebElement> trendingMovies = mapToElements(moviesTrendingBy);
        int movieIndex = (int) (Math.random() * (trendingMovies.size()-1));

        log.info("Selected index " + movieIndex);
        log.info("Amount of movies available " + trendingMovies.size());

        clickByJSExecutor(trendingMovies.get(movieIndex));

        return new MoviePage(driver);
    }

    public MovieCatalogPage clickTopRated() {
        Actions actions = new Actions(driver);

        actions.moveToElement(mapToElement(movieDropdownBy));
        actions.moveToElement(mapToElement(topRatedButtonBy));

        actions.click().build().perform();
        return new MovieCatalogPage(driver);
    }

    public LoginPage clickLoginButton() {

        waitForElementPresence(loginButtonBy);
        mapToElement(loginButtonBy).click();

        return new LoginPage(driver);
    }

    public MainPage typeMovieQuery(String name) {
        log.info("Typing in movie query");
        mapToElement(searchBarBy).sendKeys(name);
        return this;
    }

    public SearchResultsPage clickSearchButton() {

        waitForElementPresence(searchButtonBy);
        mapToElement(searchButtonBy).click();
        return new SearchResultsPage(driver);
    }

}
