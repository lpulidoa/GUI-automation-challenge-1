package driverfactory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {
    @Override
    public void createDriver() {
        driver = new ChromeDriver();
    }

}
