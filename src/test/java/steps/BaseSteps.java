package steps;

import io.cucumber.java.en.When;
import pages.BasePage;

public class BaseSteps {
    BasePage basePage = new BasePage();

    private BasePage getBasePage() {
        return basePage == null ? basePage = new BasePage() : basePage;
    }

    @When("switch {string} tab")
    public void switchTab(String tabIndex) {
        getBasePage().switchTab(tabIndex);
    }
}
