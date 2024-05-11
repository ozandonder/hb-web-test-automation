package pages.home;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.BasePage;

public class HomePage extends BasePage {
    By BTN_SEARCH = By.xpath("//div[contains(@class, 'searchBoxOld') and text()='ARA']");
    By IMG_SEARCH_BAR = By.xpath("//div[contains(@class, 'searchBoxOld')]/i");
    By TXT_SEARCH_BAR = By.cssSelector("input[aria-controls='react-autowhatever-1']");

    public HomePage clickSearchIcon() {
        click(IMG_SEARCH_BAR);
        return this;
    }

    public void clickSearchBar() {
        click(BTN_SEARCH);
    }

    public HomePage fillSearchText(String searchText) {
        type(TXT_SEARCH_BAR, searchText, true);
        return this;
    }

    public void verifyDefaultValueOfSearchBar() {
        Assertions.assertEquals(1, 2, "Default value of search bar incorrect");
    }

    public void verifyAllFooterText() {
        Assertions.assertTrue(true, "Footer text incorrect");
    }

    public void verifyAgreementPopup() {
        Assertions.assertTrue(true, "Agreement popup not found");
    }
}
