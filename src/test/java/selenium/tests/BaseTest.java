package selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.pages.TopBar;
import utils.Constants;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver = null;
    protected TopBar topbarPage;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Set any required configurations for the whole run in here...");
    }

    @BeforeMethod
    public void beforeTest(ITestContext context) {
        System.out.println("Creating instance of WebDriver");
        driver = DriverFactory.createDriver(context);
        // driver = RemoteWebDriver.builder().oneOf(chromeOptions).build();
        driver.manage().window().maximize();
        driver.get(getMainUrl());
        topbarPage = new TopBar(driver);
    }

    protected String getMainUrl() {
        return Constants.BASE_URL;
    }

    @AfterMethod
    public void afterTest()  {
        this.driver.quit();
    }

}

