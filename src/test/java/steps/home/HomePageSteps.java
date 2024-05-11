package steps.home;

import io.cucumber.java.en.When;
import pages.home.HomePage;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @When("search {string} on homepage")
    public void searchOnHomepage(String searchTerm) {
        homePage.clickSearchIcon().fillSearchText(searchTerm).clickSearchBar();
    }
}
