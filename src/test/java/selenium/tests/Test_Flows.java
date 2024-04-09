package selenium.tests;

import data_providers.DataProviderDefault;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.pages.NewsletterPage;
import selenium.pages.ShoppingCartPage;
import selenium.tests.BaseTest;
import utils.WebDriverUtils;

import java.util.Collections;
import java.util.Map;

public class Test_Flows extends BaseTest {

    @BeforeClass
    public void PrintName(){
        System.out.println("Flows Suite");
    }

    @Test(dataProvider = "Users",dataProviderClass = DataProviderDefault.class)
    public void TryingCommonFlows1(String user,String pass,String var_not_needed) {
        // Go to Newsletter, doing a login first, page and highlight main element
        NewsletterPage news_page = CommonFlows.FlowGoToNewsLetter(driver, Map.of("user", user,"pass", pass));
        WebDriverUtils.highlight(news_page.getPageLoadedElement(), driver);
        Assert.assertTrue(news_page.getPageLoadedElement().isDisplayed(),
                "Newsletter label should be displayed");
        // Return to homepage and do it again with a logged user
        CommonFlows.goToHomePage(driver);
        news_page = CommonFlows.FlowGoToNewsLetter(driver,null);
        WebDriverUtils.highlight(news_page.getPageLoadedElement(), driver);
        Assert.assertTrue(news_page.getPageLoadedElement().isDisplayed(),
                "Newsletter label should be displayed");
        // Go To Shopping Cart and highlight main element
        ShoppingCartPage shoppingCartPage = CommonFlows.goToShoppingCart(driver,null);
        WebDriverUtils.highlight(driver.findElement(shoppingCartPage.getPageLoadedLocator()), driver);
        Assert.assertTrue(driver.findElement(shoppingCartPage.getPageLoadedLocator()).isDisplayed(),
                "Shopping cart label should be displayed");

    }
}
