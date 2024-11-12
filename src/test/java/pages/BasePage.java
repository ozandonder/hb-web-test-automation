package pages;

import org.openqa.selenium.By;
import utils.WebDriverHelper;

public class BasePage extends WebDriverHelper {
    private final By BTN_ACCEPT_ONE_TRUST = By.cssSelector("#onetrust-accept-btn-handler");
    private final By LIST_LOADER_TRUE = By.cssSelector("[data-test-id='loader-true']");

    public void acceptOneTrust() {
        click(BTN_ACCEPT_ONE_TRUST);
        waitForPageToLoad();
    }

    public void switchTab(String tabIndex) {
        switchToTab(tabIndex);
    }

    public void waitForPageToLoad() {
        waitUntilElementInvisible(LIST_LOADER_TRUE);
    }
}
