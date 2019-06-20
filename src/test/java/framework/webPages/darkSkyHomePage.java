package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;
import java.lang.*;



import java.util.List;

public class darkSkyHomePage extends BasePage {

    private By darkSkyAPI = By.linkText("Dark Sky API");
    private By signUpButton = By.linkText("Sign up");
    private By registerButton = By.xpath("//button[contains(text(),'Register')]");
    private By temperature = By.xpath("//span[@class='summary swap']");
    private By lowtemp = By.xpath("//span[contains(text(),'Low:')]");
    private By hightemp =By.xpath("//span[contains(text(),'High:')]");


    public void clickOnDarkSkyAPI() {
        clickOn(darkSkyAPI);
    }

    public void signUpButton() {
        clickOn(signUpButton);
    }

    public void registerButton() {
        clickOn(registerButton);
    }

    public void temperatureValidation() {
        String temp = getTextFromElement(temperature);
        int currentTemp = Integer.parseInt(temp.substring(0, 2));
        int high = currentTemp;
        int low = currentTemp;
        List<WebElement> timeLineTemps = SharedSD.getDriver().findElements(By.xpath("//span[contains(@style,'opacity')]"));
        for (WebElement timeLineTemp : timeLineTemps) {
            String tempe = timeLineTemp.getText();
            int tempLine = Integer.parseInt(tempe.substring(0, 2));
            if (tempLine > high) {
                high = tempLine;
            }
            if (tempLine < low) {
                low = tempLine;
            }
        }
        System.out.println("Current Temperature is " + currentTemp + " which is valid for today's Highest Temp " + high +
                " and Lowest Temp " + low );
    }
}
