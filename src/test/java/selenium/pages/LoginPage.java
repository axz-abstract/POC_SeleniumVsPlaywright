package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By inputPass = By.id("input-password");
    private final By btnLogin = By.cssSelector("input[value='Login']");

    /**
     * The basic Page Object constructor
     * It waits until the element finder in the method is found or the timer has finished.
     * On the second case a Timeout Exception is thrown
     *
     * @param driver the driver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getPageLoadedLocator() {
        return By.id("input-email");
    }

    public MyAccountPage login(String user, String pass){
        driver.findElement(getPageLoadedLocator()).sendKeys(user);
        driver.findElement(inputPass).sendKeys(pass);
        driver.findElement(btnLogin).click();
        return new MyAccountPage(driver);
    }
}
