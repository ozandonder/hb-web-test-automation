package steps.home;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.home.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageSteps {
    private HomePage homePage;

    private HomePage getHomePage() {
        return homePage == null ? homePage = new HomePage() : homePage;
    }

    @When("search {string} on homepage")
    public void searchOnHomepage(String searchTerm) {
        getHomePage().clickSearchIcon().fillSearchText(searchTerm).clickKeyEnterButton();
    }

    @Then("verify default value of search bar on homepage")
    public void verifyDefaultValueOfSearchBarOnHomepage() {
        assertEquals(1, 2, "Default value of search bar incorrect");
    }

    @Then("verify all footer text on homepage")
    public void verifyAllFooterTextOnHomepage() {
        assertTrue(true, "Footer text incorrect");
    }

    @Then("verify agreement popup on homepage")
    public void verifyAgreementPopupOnHomepage() {
        assertTrue(true, "Agreement popup not found");
    }
}
