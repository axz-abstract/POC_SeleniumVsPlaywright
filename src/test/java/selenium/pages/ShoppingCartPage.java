package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

    /**
     * The basic Page Object constructor
     * It waits until the element finder in the method is found or the timer has finished.
     * On the second case a Timeout Exception is thrown
     *
     * @param driver the driver
     */
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getPageLoadedLocator() {
        return By.cssSelector(".breadcrumb a[href$=cart]");
    }

}
