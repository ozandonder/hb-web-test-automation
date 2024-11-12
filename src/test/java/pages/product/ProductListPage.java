package pages.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.ResourceFileReader;
import utils.UtilityMethods;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ProductListPage extends BasePage {
    private ResourceFileReader resourceFileReader;
    private final By BTN_PRODUCT_CARD = By.cssSelector("ul[class*='productListContent'] li[class*='productListContent']");
    private final String CHK_SORTING_TYPE = "//input[@value='%s']/parent::label";
    private final By IMG_SORTING = By.cssSelector("i[class*='horizontalSortingBar']");

    private ResourceFileReader getResourceFileReader() {
        return resourceFileReader == null ? resourceFileReader = new ResourceFileReader() : resourceFileReader;
    }

    public void clickRandomProductCard() {
        List<WebElement> productList = findElements(BTN_PRODUCT_CARD);
        if (productList.isEmpty()) {
            fail("Product list not found! Please ensure that the products are loaded and the correct locator is being used.");
        }
        productList.get(UtilityMethods.getRandomIndex(productList.size() - 1)).click();
    }

    public void productListSorting(String sortingType) {
        click(IMG_SORTING);
        By shortingType = xpathByVar(CHK_SORTING_TYPE, getResourceFileReader().getResourceData("product_list_page", sortingType));
        waitUntilElementPresent(shortingType);
        click(shortingType);
        waitForPageToLoad();
    }
}
