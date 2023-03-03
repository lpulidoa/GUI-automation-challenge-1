package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class LoginPage extends BasePage {
    private static final Logger log = getLogger(LoginPage.class.getName());
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private final By usernameBarBy = By.xpath("//input[@id='username']");
    private final By passwordBarBy = By.xpath("//input[@id='password']");
    private final By loginConfirmationButtonBy = By.xpath("//input[@id='login_button']");
    private final By mainLoginErrorBy = By.xpath("//div[@class='carton']//span");
    private final By secondaryLoginErrorsBy = By.xpath("//div[@class='carton']//div[@class= 'content']//li");


    public LoginPage typeUsername(String username){
        log.info("Typing in username credential");
        mapToElement(usernameBarBy).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password){
        log.info("Typing in password credential");
        mapToElement(passwordBarBy).sendKeys(password);
        return this;
    }

// Se podría hacer un solo método de click que devuelva una base page, luego de una revisión del url actual devolver la misma loginPage o un profilePage nuevo
    public LoginPage clickLoginConfirmationButton(){
        mapToElement(loginConfirmationButtonBy).click();
        return this;
    }
    public ProfilePage clickLoginConfirmationButtonSuccessfully(){
        log.info("The confirmation button is clicked after typing the correct credentials");
        clickLoginConfirmationButton();
        return new ProfilePage(driver);
    }

    public LoginPage clickLoginConfirmationButtonUnsuccessfully(){
        log.info("The confirmation button is clicked after typing the incorrect credentials or not leaving an input blank");
        clickLoginConfirmationButton();
        return this;
    }
//

    public String getMainLoginError() {
        log.info("The main error message in located and compared");
        return mapToElement(mainLoginErrorBy).getText();
    }

    public List<String> getSecondaryLoginErrors() {
        log.info("The secondary error messages are located and compared");
        List<String> messages = new ArrayList<>();

        for(int i=0; i<2; i++){
            messages.add(mapToElements(secondaryLoginErrorsBy).get(i).getText());
        }

        return messages;
    }


}
