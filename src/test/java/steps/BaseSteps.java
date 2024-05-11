package steps;

import io.cucumber.java.en.When;
import pages.BasePage;

public class BaseSteps {
    BasePage basePage = new BasePage();

    @When("switch {string} tab")
    public void switchTab(String tabIndex) {
        basePage.switchTab(tabIndex);
    }
}
