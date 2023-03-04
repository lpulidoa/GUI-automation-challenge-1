package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MovieCatalogPage extends BasePage{
    private static final Logger log = getLogger(MovieCatalogPage.class.getName());
    public MovieCatalogPage(WebDriver driver) {
        super(driver);
    }
    private final By filterButtonBy = By.xpath("//div[@data-callback='filterCallback()']");
    private final By filterSearchButtonBy = By.xpath("//a[@class='no_click load_more'][normalize-space()='Search']");
    private final By filteredMoviesBy = By.xpath("//div[@class= 'media_items results']//h2//a[contains(@href,'/movie/')]");
    private final By htmlBy = By.xpath("//html");
    private final By sortDropdownBy = By.xpath("//span[contains(text(),'Rating Descending')]");
    private final By ascendingDateSortBy = By.xpath("//li[contains(text(),'Release Date Ascending')]");
    private final By dateSortedMoviesBy = By.xpath("//div[@class= 'media_items results']//p");

    public List<Date> getSortedMoviesDate() {
        List<Date> dates = new ArrayList<>();
        List<WebElement> datesBy = mapToElements(dateSortedMoviesBy);

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        for (int i=0; i<4; i++) {
            String dateString= datesBy.get(i).getText();
            try {
                Date date = formatter.parse(dateString);
                dates.add(date);
            } catch (ParseException e) {
                log.error("Error while parsing date");
            }
        }

        return dates;
    }

    public MovieCatalogPage clickSortDateAscending() {
        waitForElementPresence(ascendingDateSortBy);

        mapToElement(ascendingDateSortBy).click();
        return this;
    }

    public MovieCatalogPage clickSortDropdown() {
        waitForElementPresence(sortDropdownBy);

        mapToElement(sortDropdownBy).click();
        return this;
    }

    public MoviePage selectAnyMovie() {

        log.info("Click on ANY movie");

        List<WebElement> filteredResults = mapToElements(filteredMoviesBy);
        int movieIndex = (int) (Math.random() * (filteredResults.size()-1));

        log.info("Selected index " + movieIndex);

        clickByJSExecutor(filteredResults.get(movieIndex));

        return new MoviePage(driver);
    }

    public MovieCatalogPage waitForFilterApplication(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(htmlBy, "class","nprogress-busy")));
        return this;
    }

    public MovieCatalogPage clickFilterButton(){
        mapToElement(filterButtonBy).click();
        return this;
    }

    public MovieCatalogPage clickSearchButton(){
        clickByJSExecutor(mapToElements(filterSearchButtonBy).get(0));
        return this;
    }
}
