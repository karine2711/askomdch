package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    public WebDriver initializeDriver(String browser) {

        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().
                window().maximize();
        return driver;
    }
}
