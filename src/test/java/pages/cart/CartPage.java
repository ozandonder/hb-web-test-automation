package pages.cart;

import globals.Product;
import pages.BasePage;
import utils.UtilityMethods;

public class CartPage extends BasePage {
    private final String LBL_PRODUCT_TOTAL_PRICE = "//div[contains(@class,'product_name')]/a[text()='%s']//ancestor::div[contains(@class,'product_box')]//div[contains(@class,'product_price_container')]//div[contains(@class,'product_price_')]";

    public Boolean verifyProductTotalPrice() {
        String productTotalPriceText = getText(xpathByVar(LBL_PRODUCT_TOTAL_PRICE, Product.getInstance().getProductName()));
        Float price = Float.parseFloat(Product.getInstance().getPriceText().replace(".", "").replace(",", "."));
        return productTotalPriceText.equals(UtilityMethods.numberFormating(price) + " " + Product.getInstance().getCurrencyText());
    }
}
