package org.selenium.pom.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(product.getName(), cartPage.getProdName());
    }


}
