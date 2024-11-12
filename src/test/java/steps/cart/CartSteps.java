package steps.cart;

import io.cucumber.java.en.Then;
import pages.cart.CartPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {
    private CartPage cartPage;

    private CartPage getCartPage() {
        return cartPage == null ? cartPage = new CartPage() : cartPage;
    }

    @Then("verify product price on cart page")
    public void verifyProductPriceOnCartPage() {
        assertTrue(getCartPage().verifyProductTotalPrice(), "Price does not match");
    }
}
