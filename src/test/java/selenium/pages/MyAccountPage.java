package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{

    private final By linkNewsletter = By.linkText("Newsletter");
    private final By successAlert = By.className("alert-success");

    /**
     * The basic Page Object constructor
     * It waits until the element finder in the method is found or the timer has finished.
     * On the second case a Timeout Exception is thrown
     *
     * @param driver the driver
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public NewsletterPage goToNewsletter(){
        driver.findElement(linkNewsletter).click();
        return new NewsletterPage(driver);
    }

    @Override
    public By getPageLoadedLocator() {
        return By.id("account-account");
    }

    public String getAlertMessage(){
        return driver.findElement(successAlert).getText();
    }
}
