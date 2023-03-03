import Factory.DriverManager;
import Factory.DriverManagerFactory;
import Factory.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Hooks {

    DriverManager driverManager;
    protected static WebDriver driver;

    @BeforeClass
    public void chooseBrowser(){
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void setUp(){

        driver = driverManager.getDriver();
        driver.manage()
                .window()
                .maximize();
        driver.navigate()
                .to("https://www.themoviedb.org/");
    }

    @AfterMethod
    public void tearDown(){
        driverManager.quitDriver();
    }

    public static synchronized WebDriver getDriver() {
        return driver;
    }

}
