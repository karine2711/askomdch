package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = "td[class='product-name'] a")
    private WebElement prodName;
    @FindBy(css = ".checkout-button")
    private WebElement checkoutBtn;
    @FindBy(css = ".has-text-align-center")
    @CacheLookup
    private WebElement cartHeading;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.textToBePresentInElement(cartHeading, "Cart"));
    }

    public String getProdName() {
        return wait.until(ExpectedConditions.visibilityOf(prodName)).getText();
    }

    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}
