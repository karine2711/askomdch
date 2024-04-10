package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

import java.security.PublicKey;

public class StorePage extends BasePage {
    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By addToCartBtn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    private StorePage enterTextInSearchField(String txt) {
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    private StorePage clickSearchBtn() {
        driver.findElement(searchBtn).click();
        return this;
    }

    public StorePage search(String txt) {
        enterTextInSearchField(txt).clickSearchBtn();
        return this;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    private By getAddToCartElement(String prodName) {
        return By.cssSelector("a[aria-label='Add “" + prodName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String prodName) {
        By addToCartBtn = getAddToCartElement(prodName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public StorePage addProductsToCart(int number) {
        driver.findElements(By.className("product-type-simple"))
                .stream()
                .limit(number)
                .forEach(e -> {
                    e.findElement(By.className("add_to_cart_button")).click();
                 });
        return this;
    }

    public int countProductsOnPage() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("product-type-simple")));
        return driver.findElements(By.className("product-type-simple")).size();
    }

    public CartPage clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
