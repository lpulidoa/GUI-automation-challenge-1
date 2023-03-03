package driverfactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;
    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
        }

    }

    public WebDriver getDriver() {
        createDriver();
        return driver;
    }
}
