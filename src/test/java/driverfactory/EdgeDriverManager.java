package driverfactory;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {
    @Override
    public void createDriver() {
        driver = new EdgeDriver();
    }
}
