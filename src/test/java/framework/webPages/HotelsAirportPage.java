package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HotelsAirportPage extends BasePage {

    private By distanceLink = By.linkText("Distance");
    private By airPortLink = By.linkText("Logan International Airport (BOS)");
    Float distanceRange;

    public void clickOnDistance() {
        clickOn(distanceLink);
        clickOn(airPortLink);
    }

    public void getRadiusOfDisplayedHotels() throws InterruptedException {
        SharedSD.getDriver().manage().window().maximize();
        List<WebElement> hotels = SharedSD.getDriver().findElements(By.xpath("//ul[@class='property-landmarks']/child::li[1]"));
        //********Only checking the first 10 hotels with in 10 miles radius or not*******
        for (WebElement distance : hotels) {
            String[] dis = (distance.getText().split("miles"));
            distanceRange = Float.parseFloat(dis[0]);
            Thread.sleep(1000);
        }
        boolean result = false;

        if (distanceRange < 10) {
            result = true;
        }
        Assert.assertTrue(result);
    }


    public void hiltonIsInRadius()
    {
        SharedSD.getDriver().manage().window().maximize();
        boolean result = false;
        //Verify Between the first 10 hotels with in 10 miles of radius Hilton Hotel is there
        List<WebElement> hotelNames = SharedSD.getDriver().findElements(By.xpath("//a[@class='property-name-link']"));
        for (WebElement hotelName : hotelNames)
        {
            String hotel = hotelName.getText();

            if(hotel.contains("Hilton"))
            {
                result= true;
            }

        }
        Assert.assertTrue(result);
    }


}

