package steps.product;

import io.cucumber.java.en.And;
import pages.product.ProductListPage;

public class ProductListSteps {
    private ProductListPage productListPage;

    private ProductListPage getProductListPage() {
        return productListPage == null ? productListPage = new ProductListPage() : productListPage;
    }

    @And("click random product card and go to product detail on product list page")
    public void clickFirstProductCardAndGoToProductDetailOnProductListPage() {
        getProductListPage().clickRandomProductCard();
    }

    @And("sort product list {string} type on product list page")
    public void searchOnHomepage(String sortingType) {
        getProductListPage().productListSorting(sortingType);
    }
}
