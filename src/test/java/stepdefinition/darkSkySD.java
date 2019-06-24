package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.HomePage;
import framework.webPages.LoginPage;
import framework.webPages.darkSkyHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class darkSkySD {

  private darkSkyHomePage darksky = new darkSkyHomePage();

  @Given("^I am on darkSky home page$")
  public void iAmOnHomePage()
  {

    String homePageURL = SharedSD.getDriver().getCurrentUrl();
    Assert.assertTrue(homePageURL.contains("https://darksky.net"), "We are On Dark Sky HomePage");
  }
  @When("^I click on DarkSky API button$")
  public void clickAPILink() { darksky.clickOnDarkSkyAPI(); }

  @When("^I click on SignUp button$")
  public void clickSignUp() { darksky.signUpButton(); }

  @When("^I click on Register button$")
  public void clickOnRegisterButton() { darksky.registerButton(); }

  @Then("^I verify i am still on Register Page$")
  public void validateRegisterURL()
  {
    Assert.assertEquals(SharedSD.getDriver().getCurrentUrl(),"https://darksky.net/dev/register");
  }

  @Then("^I verify current temperature is not greater or less then temps from daily timeline$")
  public void validateTemperateValue()
  {
   darksky.temperatureValidation();
  }

  @When("^I expand today's timeline$")
   public void scrollToWeeklyTemperature() throws InterruptedException
  {
     darksky.scrollToDetails();
     Thread.sleep(100);
  }

  @Then("^I verify lowest and highest temp is displayed correctly$")
  public void verifyCurrentTimeline()
  {

    darksky.currentTimelineValidation();
  }
  @Then("^I verify timeline is displayed with two hours incremented$")
  public void timeLineIncrementedCorrectly()
  {
     darksky.timeLineIncrement();
  }

}
