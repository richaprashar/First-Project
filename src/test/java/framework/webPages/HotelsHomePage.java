package framework.webPages;

import gherkin.lexer.Th;
import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class HotelsHomePage extends BasePage {

    private By inPutDestination = By.id("qf-0q-destination");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By fiveStar = By.id("f-star-rating-5");
    private By fourStar = By.id("f-star-rating-4");
    private By threeStar = By.id("f-star-rating-3");
    ArrayList<String> fiveStarsList = new ArrayList<>();
    ArrayList<String> fourStarsList = new ArrayList<>();
    ArrayList<String> threeStarsList = new ArrayList<>();


    public void forScroll() {
        JavascriptExecutor js = (JavascriptExecutor) SharedSD.getDriver();
        js.executeScript("window.scrollBy(0,600)");

    }

    public void resultSearchScreen() {
        setValue(inPutDestination, "Logan International Airport (BOS),");
        clickOn(searchButton);
    }

    public void selectFiveStars() {
        SharedSD.getDriver().manage().window().maximize();
        clickOn(fiveStar);
        List<WebElement> fiveStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text star-rating-text-strong']"));
        for (int i = 0; i < fiveStarHotels.size(); i++) {
            forScroll();
            fiveStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text star-rating-text-strong']"));
            if (fiveStarHotels.size() > 50 || isElementDisplayed(By.id("result-info-container"))) {
                break;
            } else {

                for (WebElement hotel : fiveStarHotels) {

                    fiveStarsList.add(hotel.getText().substring(0,hotel.getText().length() - 5));
                }
            }
        }
        System.out.println(fiveStarsList);
    }

    public void selectFourStars() {
        SharedSD.getDriver().manage().window().maximize();
        clickOn(fourStar);
        List<WebElement> fourStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text star-rating-text-strong']"));
        for (int i = 0; i < fourStarHotels.size(); i++) {
            forScroll();
            fourStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text star-rating-text-strong']"));
            if (fourStarHotels.size() > 50 || isElementDisplayed(By.id("result-info-container"))) {
                break;
            } else {
                for (WebElement hotel : fourStarHotels) {
                    fourStarsList.add(hotel.getText().substring(0,hotel.getText().length() - 5));
                }
            }
        }
        System.out.println(fourStarsList);
    }

    public void selectThreeStars() {
        SharedSD.getDriver().manage().window().maximize();
        clickOn(threeStar);
        List<WebElement> threeStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text']"));
        for (int i = 0; i < threeStarHotels.size(); i++) {
            forScroll();
            threeStarHotels = SharedSD.getDriver().findElements(By.xpath("//div[@class='additional-details resp-module']//span[@class='star-rating-text']"));
            if(threeStarHotels.size() > 50 || isElementDisplayed(By.id("result-info-container"))){
                break;
            }else {
                for (WebElement hotel : threeStarHotels) {
                    threeStarsList.add(hotel.getText().substring(0,hotel.getText().length() - 5));
                }
            }
        }
        System.out.println(threeStarsList);
    }

    public void verifyTheFiveStarResult()
    {
        boolean value = false;
        List<Integer> newFive = new ArrayList<Integer>(fiveStarsList.size()) ;
        for (String myInt : fiveStarsList)
        {
            newFive.add(Integer.valueOf(myInt));
        }

        for(int i=0;i<newFive.size();i++){
            if(newFive.get(i)== 5)
            {
              value = true;
            }

        }
        System.out.println(value);
        Assert.assertTrue(value);
    }
    public void verifyTheFourStarResult()
    {
        boolean value = false;
        List<Float> newFour = new ArrayList<Float>(fourStarsList.size()) ;
        for (String myInt : fourStarsList)
        {
            newFour.add(Float.valueOf(myInt));
        }

        for(int i=0;i<newFour.size();i++){
            if(newFour.get(i)>= 4 && newFour.get(i)<5)
            {
                value = true;
            }

        }
        System.out.println(value);
        Assert.assertTrue(value);
    }
    public void verifyTheThreeStarResult()
    {
        boolean value = false;
        List<Float> newThree = new ArrayList<Float>(threeStarsList.size()) ;
        for (String myInt : threeStarsList)
        {
            newThree.add(Float.valueOf(myInt));
        }

        for(int i=0;i<newThree.size();i++){
            if(newThree.get(i)>= 3 && newThree.get(i)<4)
            {
                value = true;
            }

        }
        System.out.println(value);
        Assert.assertTrue(value);
    }

}

