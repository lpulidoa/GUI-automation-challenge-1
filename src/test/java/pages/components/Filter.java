package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.MovieCatalogPage;

import java.util.List;
import java.util.stream.Collectors;


public class Filter extends BasePage {
    public Filter(WebDriver driver) {
        super(driver);
    }
    private final By genreFilterButtonsBy = By.xpath("//div[@class= 'filter']//ul[@id= 'with_genres']//a");

    public MovieCatalogPage applyMovieGenreFilter(String genre){
        List<WebElement> genresList = mapToElements(genreFilterButtonsBy).stream()
                .filter(element -> element.getText().equals(genre))
                .collect(Collectors.toList());
        clickByJSExecutor(genresList.get(0));
        return new MovieCatalogPage(driver);
    }

}
