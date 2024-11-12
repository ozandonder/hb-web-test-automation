package steps.product;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.product.ProductDetailPage;
import utils.ResourceFileReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailSteps {
    private ProductDetailPage productDetailPage;
    private ResourceFileReader resourceFileReader;

    private ProductDetailPage getProductDetailPage() {
        return productDetailPage == null ? productDetailPage = new ProductDetailPage() : productDetailPage;
    }

    private ResourceFileReader getResourceFileReader() {
        return resourceFileReader == null ? resourceFileReader = new ResourceFileReader() : resourceFileReader;
    }

    @And("add product to cart and go to cart on product detail page")
    public void addProductToCartAndGoToCartOnProductDetailPage() {
        getProductDetailPage().clickAddToCartButton().clickGoToCartButton();
    }

    @And("click first review thumbs up button on product detail page")
    public void clickFirstReviewThumbsUpButtonOnProductDetailPage() {
        getProductDetailPage().clickFirstReviewThumbsUpButton();
    }

    @And("select review tab on product detail page")
    public void selectReviewTabOnProductDetailPage() {
        getProductDetailPage().clickProductReviewTab();
    }

    @And("set product detail on product detail page")
    public void setProductDetailOnProductDetailPage() {
        getProductDetailPage().setProductDetail();
    }

    @And("sort review list {string} type on product detail page")
    public void sortReviewListTypeOnProductDetailPage(String sortType) {
        getProductDetailPage().reviewSorting(getResourceFileReader().getResourceData("product_detail_page", sortType));
    }

    @Then("verify thank you message after review useful feedback on product detail page")
    public void verifyThankYouMessageAfterReviewUsefulFeedbackOnProductDetailPage() {
        String thankYouMessage = getResourceFileReader().getValidationData("product_detail_page", "thank_you_message_for_review_thumps");
        assertTrue(getProductDetailPage().isPageHasFirstFeedbackThanksMessage(thankYouMessage), "Thank you message incorrect");
    }
}
