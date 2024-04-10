package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait((driver), Duration.ofSeconds(15));

    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitOverlaysToDisappear(By  overlay){
        List<WebElement> overlays = Collections.singletonList(driver.findElement(overlay));
        System.out.println("OVERLAY SIZE: " + overlays.size());
        if(!overlays.isEmpty()){
            wait.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS ARE INVISIBLE: ");
        } else{
            System.out.println("OVERLAY NOT FOUND");
        }
    }
}
