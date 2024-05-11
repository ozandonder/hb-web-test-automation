package steps.product;

import io.cucumber.java.en.And;
import pages.product.ProductListPage;

public class ProductListSteps {
    ProductListPage productListPage = new ProductListPage();

    @And("click first product card and go to product detail on product list page")
    public void clickFirstProductCardAndGoToProductDetailOnProductListPage() {
        productListPage.clickFirstProductCard();
    }

    @And("sort product list {string} type on product list page")
    public void searchOnHomepage(String sortingType) {
        productListPage.productListSorting(sortingType);
    }
}
