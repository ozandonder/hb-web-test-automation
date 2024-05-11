package pages.product;

import org.openqa.selenium.By;
import pages.BasePage;
import utils.ResourceFileReader;

public class ProductListPage extends BasePage {
    ResourceFileReader resourceFileReader = new ResourceFileReader();
    By BTN_FIRST_PRODUCT_CARD = By.cssSelector("ul[class*='productListContent'] li[class*='productListContent']:first-child");
    String CHK_SORTING_TYPE = "//input[@value='%s']/parent::label";
    By IMG_SORTING = By.cssSelector("i[class*='horizontalSortingBar']");

    public void clickFirstProductCard() {
        click(BTN_FIRST_PRODUCT_CARD);
    }

    public void productListSorting(String sortingType) {
        click(IMG_SORTING);
        click(xpathByVar(CHK_SORTING_TYPE, resourceFileReader.getResourceData("product_list_page", sortingType)));
        waitForPageToLoad();
    }
}
