import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Hooks {

    protected static WebDriver driver;


    @BeforeMethod
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage()
                .window()
                .maximize();
        driver.navigate()
                .to("https://www.themoviedb.org/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public static synchronized WebDriver getDriver() {
        return driver;
    }

}
