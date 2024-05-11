package pages;

import org.openqa.selenium.By;
import utils.WebDriverHelper;

public class BasePage extends WebDriverHelper {
    By LIST_LOADER_TRUE = By.cssSelector("[data-test-id='loader-true']");

    public void switchTab(String tabIndex) {
        switchToTab(tabIndex);
    }

    public void waitForPageToLoad() {
        waitUntilElementInvisible(LIST_LOADER_TRUE);
    }
}
