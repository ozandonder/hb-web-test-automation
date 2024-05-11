package steps.home;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.home.HomePage;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @When("search {string} on homepage")
    public void searchOnHomepage(String searchTerm) {
        homePage.clickSearchIcon().fillSearchText(searchTerm).clickSearchBar();
    }

    @Then("verify default value of search bar on homepage")
    public void verifyDefaultValueOfSearchBarOnHomepage() {
        homePage.verifyDefaultValueOfSearchBar();
    }

    @Then("verify all footer text on homepage")
    public void verifyAllFooterTextOnHomepage() {
        homePage.verifyAllFooterText();
    }

    @Then("verify agreement popup on homepage")
    public void verifyAgreementPopupOnHomepage() {
        homePage.verifyAgreementPopup();
    }
}
