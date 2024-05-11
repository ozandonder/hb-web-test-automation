package pages.product;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.ResourceFileReader;

public class ProductDetailPage extends BasePage {
    ResourceFileReader resourceFileReader = new ResourceFileReader();
    By BTN_PRODUCT_REVIEW_TAB = By.id("productReviewsTab");
    By BTN_FIRST_REVIEW_THUMBS_UP = By.cssSelector("div[itemprop='review']:first-child [class*='thumbsUp']");
    String LBL_THANK_YOU_MESSAGE_ON_REVIEW_FEEDBACK = "//div[@class='paginationContentHolder']//div[@itemprop='review'][%s]//span[contains(@class,'hermes-ReviewCard-module-') and text()='%s']";

    public void clickProductReviewTab() {
        scrollUntilElementVisible(BTN_PRODUCT_REVIEW_TAB);
        click(BTN_PRODUCT_REVIEW_TAB);
        waitForPageToLoad();
    }

    public void clickFirstReviewThumbsUpButton() {
        scrollUntilElementVisible(BTN_FIRST_REVIEW_THUMBS_UP);
        click(BTN_FIRST_REVIEW_THUMBS_UP);
    }

    public void verifyFirstFeedbackThanksMessage() {
        String thankYouMessage = resourceFileReader.getValidationData("product_detail_page", "thank_you_message_for_review_thumps");
        Assertions.assertTrue(isElementPresent(xpathByVar(LBL_THANK_YOU_MESSAGE_ON_REVIEW_FEEDBACK, "1", thankYouMessage)));
    }
}
