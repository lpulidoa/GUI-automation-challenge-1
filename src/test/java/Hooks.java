import driverfactory.DriverManager;
import driverfactory.DriverManagerFactory;
import driverfactory.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Hooks {

    DriverManager driverManager;
    protected WebDriver driver;

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

    public synchronized WebDriver getDriver() {
        return driver;
    }

}
