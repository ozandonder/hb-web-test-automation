package steps.product;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.product.ProductDetailPage;

public class ProductDetailSteps {
    ProductDetailPage productDetailPage = new ProductDetailPage();

    @And("click first review thumbs up button on product detail page")
    public void clickFirstReviewThumbsUpButtonOnProductDetailPage() {
        productDetailPage.clickFirstReviewThumbsUpButton();
    }

    @And("select review tab on product detail page")
    public void selectReviewTabOnProductDetailPage() {
        productDetailPage.clickProductReviewTab();
    }

    @Then("verify thank you message after review useful feedback on product detail page")
    public void verifyThankYouMessageAfterReviewUsefulFeedbackOnProductDetailPage() {
        productDetailPage.verifyFirstFeedbackThanksMessage();
    }
}
