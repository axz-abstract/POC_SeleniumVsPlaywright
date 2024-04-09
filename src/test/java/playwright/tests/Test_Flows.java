package playwright.tests;

import data_providers.DataProviderDefault;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playwright.pages.Newsletter_Page;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test_Flows extends BaseTest{

    @BeforeClass
    public void PrintName(){
        System.out.println("Flows Suite");
    }

    @Test(dataProvider = "Users",dataProviderClass = DataProviderDefault.class)
    public void TestFlows_PW(String user, String pass, String var_not_needed){
        // Go to newsletter page and highlight main element
        Newsletter_Page newsletterPage = CommonFlows.goToNewsletterPage(mainPage, Map.of("user",user,"pass",pass));
        newsletterPage.getPageLoadedLocator().highlight();
        assertThat(newsletterPage.getPageLoadedLocator()).isVisible();
        // Go to homepage
        CommonFlows.goToHomePage(mainPage);
        // Go to newsletter again without logging in
        newsletterPage = CommonFlows.goToNewsletterPage(mainPage, null);
        newsletterPage.getPageLoadedLocator().highlight();
        assertThat(newsletterPage.getPageLoadedLocator()).isVisible();
        mainPage.waitForTimeout(1000);
    }

}
