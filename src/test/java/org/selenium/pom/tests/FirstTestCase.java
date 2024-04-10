package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.*;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutWithDirectBankTransfer() throws IOException {
//        BillingAddress billingAddress = new BillingAddress();
//        billingAddress.setFirstName("demo").
//                setLastName("user").
//                setAddressLineOne("San Francisco").
//                setCity("San Francisco").
//                setPostalCode("94188").
//                setEmail("test1@example.com");
//        BillingAddress billingAddress = new BillingAddress("demo", "user",
//                "San Francisco", "San Francisco",
//                "94188", "test1@example.com");
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
//        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        Assert.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle());

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
//        Assert.assertEquals(cartPage.getProdName(), product.getName());
        Assert.assertEquals(product.getName(), cartPage.getProdName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer()
                .clickPlaceOrderBtn();
//        Assert.assertEquals(checkoutPage.getNotice(),
//                "Thank you. Your order has been received.");
        Assert.assertEquals("Thank you. Your order has been received.",
                checkoutPage.getNotice());
    }

    @Test
    public void loginAndCheckoutWithDirectBankTransfer() throws IOException {
//        BillingAddress billingAddress = new BillingAddress("demo", "user",
//                "San Francisco", "San Francisco",
//                "94188", "test1@example.com");
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
//        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        Assert.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle());

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
//        Assert.assertEquals(cartPage.getProdName(), product.getName());
        Assert.assertEquals(product.getName(), cartPage.getProdName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.openLoginSection().
                loginWithExistingUser(user.getUsername(), user.getPassword()).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrderBtn();
//        Assert.assertEquals(checkoutPage.getNotice(),
//                "Thank you. Your order has been received.");
        Assert.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice());
    }
}
