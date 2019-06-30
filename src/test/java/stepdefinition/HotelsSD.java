package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.HotelsHomePage;
import org.testng.Assert;

public class HotelsSD {

    private HotelsHomePage hotelHome = new HotelsHomePage();

    @Given("^I am on default locations search result screen$")
    public void iAmOnSearchScreen() throws InterruptedException
    {
       SharedSD.getDriver().navigate().refresh();
        hotelHome.resultSearchScreen();
        //Thread.sleep(1000);
    }

    @When("^I select property class (.+)$")
    public void iAmSelectingStars(String stars)
    {
        switch (stars) {
            case "5 stars":
                hotelHome.selectFiveStars();
                break;
            case "4 stars":
                hotelHome.selectFourStars();
                break;
            case "3 stars":
                hotelHome.selectThreeStars();
        }

    }

    @Then("^I verify system displays only (.+) hotels on search result$")
    public void verifyTheExpectedResult(String stars)
    {
        switch (stars) {
            case "5 stars":
                hotelHome.verifyTheFiveStarResult();
                break;
            case "4 stars":
                hotelHome.verifyTheFourStarResult();
                break;
            case "3 stars":
                hotelHome.verifyTheThreeStarResult();

        }


    }

}
