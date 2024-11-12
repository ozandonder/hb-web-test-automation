package pages.home;

import org.openqa.selenium.By;
import pages.BasePage;

public class HomePage extends BasePage {
    private final By IMG_SEARCH_BAR = By.xpath("//div[contains(@class, 'SearchBoxOld')]//i");
    private final By TXT_SEARCH_BAR = By.cssSelector("input[data-test-id='search-bar-input']");

    public HomePage clickSearchIcon() {
        click(IMG_SEARCH_BAR);
        return this;
    }

    public HomePage fillSearchText(String searchText) {
        waitUntilElementVisible(TXT_SEARCH_BAR);
        type(TXT_SEARCH_BAR, searchText, true);
        return this;
    }
}
