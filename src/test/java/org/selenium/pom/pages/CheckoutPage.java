package org.selenium.pom.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class CheckoutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressFld = By.id("billing_address_1");
    private final By cityFld = By.id("billing_city");
    private final By postcodeFld = By.id("billing_postcode");
    private final By emailFld = By.id("billing_email");
    private final By orderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By loginLink = By.cssSelector(".showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    private final By alternativeStateDropDown = By.id("select2-billing_state-container");
    private final By alternativeCountryDropDown = By.id("select2-billing_country-container");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressFld));
        e.clear();
        e.sendKeys(address);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
//        Select select = new Select(driver.findElement(countryDropDown));
//        select.selectByVisibleText(countryName);
        wait.until(ExpectedConditions.elementToBeClickable(alternativeCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage selectState(String stateName) {
        wait.until(ExpectedConditions.elementToBeClickable(alternativeStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(cityFld));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostcode(String postcode) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(postcodeFld));
        e.clear();
        e.sendKeys(postcode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                selectState(billingAddress.getState()).
                enterAddress(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostcode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    public CheckoutPage clickPlaceOrderBtn() {
        waitOverlaysToDisappear(overlay);
        driver.findElement(orderBtn).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage openLoginSection() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }

    public CheckoutPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public CheckoutPage loginWithExistingUser(String username, String password) {
        return enterUsername(username).
                enterPassword(password).
                clickLoginBtn();
    }
}
