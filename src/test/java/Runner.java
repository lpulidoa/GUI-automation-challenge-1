import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pages.*;

import java.util.Date;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Runner extends Hooks {
// webdriver factory con dos clases, screenshots, git.ignore(chromedriver, target, .idea)
// collections are data structures in java : collection [map (hashmap, hashset), lists (arraylist , linkedlists)]
private static final Logger log = getLogger(MainPage.class.getName());

    @Test
    @Feature("Feature1: Login")
    @Severity(SeverityLevel.BLOCKER)
    public void successfulLoginTest(){
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.typeUsername(System.getenv("USERNAME_MDB"))
                .typePassword(System.getenv("CORRECT_PSSW_MDB"));

        ProfilePage profilePage = loginPage.clickLoginConfirmationButtonSuccessfully();

        assertThat("The credentials were not correct",
                profilePage.getUsername(), equalTo(System.getenv("USERNAME_MDB")));
    }

    @Test(enabled = false)
    @Feature("Feature1: Login")
    public void unsuccessfulLoginTest(){
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.typeUsername(System.getenv("USERNAME_MDB"))
                .typePassword(System.getenv("INCORRECT_PSSW_MDB"))
                .clickLoginConfirmationButtonUnsuccessfully();

        assertThat("The user should not have been able to log in",
                loginPage.getMainLoginError(), equalTo("  There was a problem"));

        assertThat("The user should not have been able to log in",
                loginPage.getSecondaryLoginErrors().get(0), equalTo("We couldn't validate your information. Want to try again?"));

        assertThat("The user should not have been able to log in",
                loginPage.getSecondaryLoginErrors().get(1), equalTo("You have 9 remaining login attempts."));
    }

    @Test
    @Feature("Feature2: Search")
    public void successfulSearchTest(){
        String movieName = "Fight Club";

        MainPage mainPage = new MainPage(driver);

        mainPage.typeMovieQuery(movieName);

        SearchResultsPage resultsPage = mainPage.clickSearchButton();

        assertThat("The movie title is not correct",
                resultsPage.getFirstResultTitle(), equalTo(movieName));
    }

    @Test
    @Feature("Feature3: Filter")
    public void verifyMovieGenreFilter() {

        MainPage mainPage = new MainPage(driver);

        MovieCatalogPage movieCatalog = mainPage.clickTopRated()
                .clickFilterButton()
                .clickActionGenreFilter()
                .clickSearchButton()
                .waitForFilterApplication();
        MoviePage movie = movieCatalog.selectAnyMovie();

        assertThat("The movie is not of the right genre",
                movie.getGenresString(), containsString("Action"));
    }

    @Test
    @Feature("Feature4: Acting Timeline")
    public void validateActingTimeline() {

        MainPage mainPage = new MainPage(driver);

        MoviePage moviePage = mainPage.clickTrendingMovie();

        String movieName = moviePage.getName();

        ActorPage actorPage = moviePage.clickFirstCast();

        assertThat("The movie was not found in acting credits", actorPage.getActingCredits(), hasItem(movieName));
    }
//(enabled = false)
    @Test
    @Feature("Feature5: Organizing")
    public void validateDatesByAscendingOrder() {
        MainPage mainPage = new MainPage(driver);

        MovieCatalogPage movieCatalog = mainPage.clickTopRated()
                .clickSortDropdown()
                .clickSortDateAscending()
                .clickSearchButton()
                .waitForFilterApplication();

        List<Date> datesSorted = movieCatalog.getSortedMoviesDate();
        System.out.println("Sorted movidates: " + datesSorted);
        for(int i=1; i<datesSorted.size(); i++){
            assertThat("Date is not in ascending order", datesSorted.get(i) , greaterThan(datesSorted.get(i-1)) );
        }

    }

}
