package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Constants;
import utils.WebDriverUtils;

public class TopBar extends BasePage{

    private final By dropLaptops = By.cssSelector(".dropdown > a[href$='18']");
    private final By btnShowAllLaptops = By.linkText("Show All Laptops & Notebooks");
    public final By itemCategories = By.cssSelector("#menu .nav > li");
    private final By inputSearch = By.name("search");
    private final By btnSearch = By.cssSelector("#search button");
    private final By dropMyAccount = By.linkText("My Account");
    private final By linkLogin = By.linkText("Login");
    private final By linkMyAccount = By.cssSelector(".dropdown-menu a[href*='/account']");
    private final By linkHome = By.linkText("Your Store");
    private final By linkShoppingCart = By.cssSelector("a[title='Shopping Cart']");


    /**
     * The basic Page Object constructor
     * It waits until the element finder in the method is found or the timer has finished.
     * On the second case a Timeout Exception is thrown
     *
     * @param driver the driver
     */
    public TopBar(WebDriver driver) {
        super(driver);
    }

    @Override
    public By getPageLoadedLocator() {
        return By.id("menu");
    }

    public LaptopsPage goToShowAllLaptops(){
        WebDriverUtils.MoveToElement(dropLaptops,driver);
        this.ExplicitWaitElement(
                ExpectedConditions.visibilityOfElementLocated(btnShowAllLaptops),
                Constants.TINY_TIMEOUT
        ).click();
        return new LaptopsPage(driver);
    }

    public SearchResultsPage SearchProduct(String product){
        driver.findElement(inputSearch).sendKeys(product);
        driver.findElement(btnSearch).click();
        return new SearchResultsPage(driver);
    }

    public LoginPage goToLoginPage(){
        driver.findElement(dropMyAccount).click();
        this.ExplicitWaitElement(
                ExpectedConditions.visibilityOfElementLocated(linkLogin),
                Constants.TINY_TIMEOUT
        ).click();
        return new LoginPage(driver);
    }

    public MyAccountPage goToMyAccount(){
        driver.findElement(dropMyAccount).click();
        this.ExplicitWaitElement(
                ExpectedConditions.visibilityOfElementLocated(linkMyAccount),
                Constants.TINY_TIMEOUT
        ).click();
        return new MyAccountPage(driver);
    }

    public void goToHomePage(){
        driver.findElement(linkHome).click();
        this.ExplicitWaitBoolean(
                ExpectedConditions.titleIs("Your Store"),
                Constants.SHORT_TIMEOUT
        );
    }

    public ShoppingCartPage goToShoppingCart(){
        driver.findElement(linkShoppingCart).click();
        return new ShoppingCartPage(driver);
    }


}
