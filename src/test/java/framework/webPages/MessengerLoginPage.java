package framework.webPages;

import org.openqa.selenium.By;

public class MessengerLoginPage extends BasePage{

    private By errorMessage = By.xpath("//div[contains(text(),'Incorrect email or phone number')]");

    public String getErrorMessage() {
        return getTextFromElement(errorMessage);
    }
}
