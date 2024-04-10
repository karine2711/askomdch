package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

public class LoginTest {

    @Test
    public void loginDuringCheckout() throws IOException {
        String username = "user" + UUID.randomUUID();
        User user = new User().
                setUsername(username).
                setPassword("test@1234").
                setEmail(username + "@example.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
    }
}
