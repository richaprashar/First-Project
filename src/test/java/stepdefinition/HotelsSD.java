package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.HotelsAirportPage;
import framework.webPages.HotelsHomePage;
import gherkin.lexer.Th;
import org.testng.Assert;

public class HotelsSD {

    private HotelsHomePage hotelHome = new HotelsHomePage();
    private HotelsAirportPage airportPage =new HotelsAirportPage();

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

    @Then("^I verify system displays all hotels within 10 miles radius of airport$")
    public void allHotelsWithInRadius() throws InterruptedException
    {
        airportPage.clickOnDistance();
        Thread.sleep(1000);
        airportPage.getRadiusOfDisplayedHotels();
        Thread.sleep(1000);
    }

    @Then("^I verify Hilton Hotel is within radius$")
    public void hiltonHotelCheck()
    {
        airportPage.hiltonIsInRadius();
    }

}
