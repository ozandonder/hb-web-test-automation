package pages.product;

import globals.Product;
import org.openqa.selenium.By;
import pages.BasePage;

public class ProductDetailPage extends BasePage {
    private final By BTN_PRODUCT_REVIEW_TAB = By.cssSelector("[aria-controls=Reviews]");
    private final By BTN_FIRST_REVIEW_THUMBS_UP = By.cssSelector("div[class*='hermes-ReviewCard-module']:first-child [class*='thumbsUp']");
    private final By BTN_SELECT_SORTING_TYPE = By.cssSelector("div[class*='hermes-Sort-module'] div[class*='hermes-Dropdown-module']");
    private final By BTN_ADD_TO_CART = By.cssSelector("[data-test-id=addToCart]");
    private final By BTN_GO_TO_CART = By.cssSelector("[class*=checkoutui-ProductOnBasketHeader] button:first-child");
    private final String CHK_SORTING_TYPE = "//div[contains(@class,'hermes-Sort-module-') and text()='%s']";
    private final String LBL_THANK_YOU_MESSAGE_ON_REVIEW_FEEDBACK = "//div[@class='paginationContentHolder']//div[contains(@class,'hermes-ReviewCard-module-')][%s]//span[contains(@class,'hermes-ReviewCard-module-') and text()='%s']";
    private final By LBL_PRICE_TEXT = By.cssSelector("[data-test-id=price-current-price] span:first-child");
    private final By LBL_CURRENCY_TEXT = By.cssSelector("[data-test-id=price-current-price] span:nth-child(2)");
    private final By LBL_PRODUCT_NAME_TEXT = By.cssSelector("[data-test-id=title]");
    private final By LBL_BRAND_NAME_TEXT = By.cssSelector("[data-test-id=brand]");

    public void clickProductReviewTab() {
        scrollUntilElementVisible(BTN_PRODUCT_REVIEW_TAB);
        click(BTN_PRODUCT_REVIEW_TAB);
        waitForPageToLoad();
    }

    public void clickFirstReviewThumbsUpButton() {
        scrollUntilElementVisible(BTN_FIRST_REVIEW_THUMBS_UP);
        click(BTN_FIRST_REVIEW_THUMBS_UP);
    }

    public ProductDetailPage clickAddToCartButton() {
        scrollUntilElementVisible(BTN_ADD_TO_CART);
        click(BTN_ADD_TO_CART);
        Product.getInstance().setQuantity(Product.getInstance().getQuantity() + 1);
        waitForPageToLoad();
        return this;
    }

    public void clickGoToCartButton() {
        click(BTN_GO_TO_CART);
    }

    public Boolean isPageHasFirstFeedbackThanksMessage(String thankYouMessage) {
        return isElementPresent(xpathByVar(LBL_THANK_YOU_MESSAGE_ON_REVIEW_FEEDBACK, "1", thankYouMessage));
    }

    public void reviewSorting(String sortingType) {
        scrollUntilElementVisible(BTN_SELECT_SORTING_TYPE);
        click(BTN_SELECT_SORTING_TYPE);
        click(xpathByVar(CHK_SORTING_TYPE, sortingType));
        waitForPageToLoad();
    }

    public void setProductDetail() {
        Product product = Product.getInstance();
        product.setPriceText(getText(LBL_PRICE_TEXT));
        product.setCurrencyText(getText(LBL_CURRENCY_TEXT));
        product.setBrandName(getText(LBL_BRAND_NAME_TEXT));
        String fullProductName = getText(LBL_PRODUCT_NAME_TEXT);
        String brandName = product.getBrandName();
        product.setProductName(fullProductName.replace(brandName + " ", ""));
    }
}
