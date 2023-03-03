package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ProfilePage extends BasePage{
    private static final Logger log = getLogger(ProfilePage.class.getName());
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    private final By usernameTitleBy = By.xpath("//div[@class='content_wrapper flex']//a[contains(text(),'mariana200081')]");

    public String getUsername(){
        log.info("Username in profile page checked");
        return mapToElement(usernameTitleBy).getText();
    }
}
