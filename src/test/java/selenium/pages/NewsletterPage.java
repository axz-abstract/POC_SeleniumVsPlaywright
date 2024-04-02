package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constants;

public class NewsletterPage extends BasePage {

    public final By checkYes = By.cssSelector("input[value='1']");
    public final By checkNo = By.cssSelector("input[value='0']");
    private final By btnContinue = By.cssSelector("input[value=Continue]");

    /**
     * The basic Page Object constructor
     * It waits until the element finder in the method is found or the timer has finished.
     * On the second case a Timeout Exception is thrown
     *
     * @param driver the driver
     */
    public NewsletterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getPageLoadedLocator() {
        return By.id("account-newsletter");
    }

    public WebElement getPageLoadedElement() {
        return driver.findElement(getPageLoadedLocator());
    }

    public MyAccountPage subscribe(){
        long startTime = System.currentTimeMillis();
        this.ExplicitWaitElement(
                ExpectedConditions.elementToBeClickable(checkYes),
                Constants.TINY_TIMEOUT
        ).click();
        long endTime = System.currentTimeMillis();
        System.out.println("Explicit wait set to 5 seconds, but found under " + (endTime - startTime) + " milliseconds");
        driver.findElement(btnContinue).click();
        return new MyAccountPage(driver);
    }

    public MyAccountPage unsubscribe(){
        long startTime = System.currentTimeMillis();
        this.ExplicitWaitElement(
                ExpectedConditions.elementToBeClickable(checkNo),
                Constants.TINY_TIMEOUT
        ).click();
        long endTime = System.currentTimeMillis();
        System.out.println("Explicit wait set to 5 seconds, but found under " + (endTime - startTime) + " milliseconds");
        driver.findElement(btnContinue).click();
        return new MyAccountPage(driver);
    }

    public boolean isSubscribed(){
        return driver.findElement(checkYes).isSelected();
    }
}
