package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class darkSkyHomePage extends BasePage {

    private By darkSkyAPI = By.linkText("Dark Sky API");
    private By signUpButton = By.linkText("Sign up");
    private By registerButton = By.xpath("//button[contains(text(),'Register')]");
    private By currentTemp = By.xpath("//span[@class='summary swap']");
    private By detailTimeLine = By.xpath("//a[@class='day'][@data-day='0']//span[@class='toggle']");
    private By displayedLowTemp = By.xpath("//a[@data-day='0']//span[@class='minTemp']");
    private By displayedHighTemp = By.xpath("//a[@data-day='0']//span[@class='maxTemp']");
    private By expandedLowTemp =By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']//span[@class='temp']");
    private By expandedHighTemp =By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']//span[@class='temp']");
    private By tempTimeLine = By.xpath("//div[@class='hours']//span[contains(@class,'hour')]//span[@class]");


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
        String current = getTextFromElement(currentTemp);
        String[] curr = current.split(" ", 2);
        String current1 = curr[0];
        int currentTemperature = Integer.parseInt(current1.substring(0, current1.length() - 1));
        System.out.println("Current Temperature :: " + currentTemperature);
        List<WebElement> timeLineTemps = SharedSD.getDriver().findElements(By.xpath("//span[contains(@style,'opacity')]"));
        ArrayList<String> listofTemps = new ArrayList<>();
        for (WebElement temp : timeLineTemps) {
            listofTemps.add(temp.getText().substring(0, temp.getText().length() - 1));
            Collections.sort(listofTemps);
        }
        System.out.println("Sorted List of TimeLine Temperature --> " + listofTemps);
        String high = Collections.max(listofTemps);
        String low = Collections.min(listofTemps);
        int lowTempofToday = Integer.parseInt(low);
        int highTempofToday =Integer.parseInt(high);
        System.out.println("Lowest Temperature of the Day = " + lowTempofToday);
        System.out.println("Highest Temperature of the Day = " + highTempofToday);

        boolean result = false;

        if((currentTemperature>lowTempofToday) && (currentTemperature<highTempofToday))
        {
            result = true;
        }

       Assert.assertTrue(result,"Between the limit for today's Temperature");


    }

    public void scrollToDetails() throws InterruptedException
    {

        SharedSD.getDriver().manage().window().maximize();
        JavascriptExecutor jse = (JavascriptExecutor)SharedSD.getDriver();
        jse.executeScript("window.scrollBy(0,600)");
        Thread.sleep(1000);
        clickOn(detailTimeLine);
    }

    public void currentTimelineValidation() {
        String dispLowTemp = getTextFromElement(displayedLowTemp);
        String dispHighTemp = getTextFromElement(displayedHighTemp);
        String expanLowTemp = getTextFromElement(expandedLowTemp);
        String expanHighTemp = getTextFromElement(expandedHighTemp);

        boolean value = false;
        if ((dispLowTemp.equals(expanLowTemp)) && (dispHighTemp.equals(expanHighTemp))) {
            value = true;
        }
        Assert.assertTrue(value,"Displayed and Expended Times matches");
    }

    public void timeLineIncrement(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("ha" );
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.getTime();

        String time = timeFormat.format(cal.getTime()).toLowerCase();
        System.out.println("Current-->" + time);
        boolean val = true;
        ArrayList<String> listFromTimeLine = new ArrayList<>();
        List<WebElement> timeList = SharedSD.getDriver().findElements(By.xpath("//div[@class='hours']//span[@class='hour']//span[@class]"));
        for(WebElement timeElement : timeList)
        {
            listFromTimeLine.add(timeElement.getText());
        }
       ArrayList<String> everyTwohours =new ArrayList<>();
        for(int i=0; i<=listFromTimeLine.size()-1;i++)
        {
            cal.add(Calendar.HOUR_OF_DAY,2);
            String forHours = timeFormat.format(cal.getTime()).toLowerCase();
            everyTwohours.add(forHours);
        }
        System.out.println(everyTwohours);
        System.out.println(listFromTimeLine);

        Assert.assertEquals(everyTwohours,listFromTimeLine,"Passed");

    }

}
