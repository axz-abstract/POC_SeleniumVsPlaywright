package playwright.tests;

import com.microsoft.playwright.*;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseTest {

    private Playwright playwright;
    protected Browser browser;
    protected Page mainPage;
    // continues variable can be used for cases when a data-driven test makes sense to keep executing the following scenarios in the same browser
    protected boolean continues = false;

    // To be executed before the entire suite, can be used to configure global settings beforehand
    @BeforeSuite
    public void beforeSuite(){
        playwright = Playwright.create();
        // Set a custom attribute to be found when using the method getByTestId("directions") where "directions" is the value of the data-pw attribute
        playwright.selectors().setTestIdAttribute("data-pw");
    }

    // To be executed before each test method
    @BeforeMethod
    public void setUp(ITestContext context){
//        context.getCurrentXmlTest().addParameter("browser", "chrome");
//        context.getCurrentXmlTest().addParameter("headless", String.valueOf(false));
//        context.getCurrentXmlTest().addParameter("slow", String.valueOf(500));
        createBrowser(context.getCurrentXmlTest().getAllParameters());
        mainPage = browser.newContext(new Browser.NewContextOptions().setViewportSize(null)).newPage();
        mainPage.navigate(Constants.BASE_URL);
    }

    // To be executed right after a test method
    @AfterMethod
    public void tearDown(){
        if (!continues)
            this.browser.close();
    }

    // To be executed after the whole suite
    @AfterSuite
    public void afterSuite(){
        this.playwright.close();
    }


    // Set the parameters for the test such as browser type, delay between steps (SlowMo) and headless state
    private void createBrowser(Map<String,String> parameters){
        String headless = Optional.ofNullable(parameters.get("headless")).orElse(String.valueOf(false));
        String delay = Optional.ofNullable(parameters.get("slow")).orElse(String.valueOf(0));
        String browser_param = Optional.ofNullable(parameters.get("browser")).orElse("chrome");

        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(headless))
                .setSlowMo(Double.parseDouble(delay))
                .setArgs(List.of("--start-maximized"));

        switch (browser_param.toLowerCase()){
            case "chrome":
                lp.setChannel("chrome");
                browser = this.playwright.chromium().launch(lp);
                break;
            case "edge":
                lp.setChannel("msedge");
                browser = this.playwright.chromium().launch(lp);
                break;
            case "firefox":
                browser = this.playwright.firefox().launch(lp);
                break;
            case "webkit":
                browser = this.playwright.webkit().launch(lp);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");
        }
    }


}
