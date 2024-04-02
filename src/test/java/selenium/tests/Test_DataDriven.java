package selenium.tests;

import data_providers.DataProviderDefault;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.pages.*;

public class Test_DataDriven extends BaseTest {

    @Test(dataProvider = "Products",dataProviderClass = DataProviderDefault.class)
    public void localDataProvider(String searchText){
        SearchResultsPage resultsPage = topbarPage.SearchProduct(searchText);
        Assert.assertEquals(resultsPage.getTitle(),"Search - " + searchText);
        ProductPage prodPage =  resultsPage.selectProduct(searchText);
        Assert.assertTrue(driver.getTitle().contains(searchText),
                "The page title should contain the searched text");
        Assert.assertTrue(prodPage.getProductName().contains(searchText),
                "The product name should contain the searched text");
    }

    @Test(dataProvider = "Users",dataProviderClass = DataProviderDefault.class)
    public void externalDataProvider(String user,String pass,String string_subscribe){
        boolean subscribe = Boolean.parseBoolean(string_subscribe);
        LoginPage loginPage = topbarPage.goToLoginPage();
        MyAccountPage myAccount = loginPage.login(user,pass);
        NewsletterPage newsPage = myAccount.goToNewsletter();
        if (subscribe) {
            myAccount = newsPage.subscribe();
            Assert.assertEquals("Success: Your newsletter subscription has been successfully updated!",myAccount.getAlertMessage());
            myAccount.goToNewsletter();
            Assert.assertTrue(newsPage.isSubscribed(), "The user should be subscribed");
        }
        else {
            myAccount = newsPage.unsubscribe();
            Assert.assertEquals("Success: Your newsletter subscription has been successfully updated!",myAccount.getAlertMessage());
            myAccount.goToNewsletter();
            Assert.assertFalse(newsPage.isSubscribed(), "The user should be unsubscribed");
        }
        try{ Thread.sleep(3000); }
        catch(InterruptedException e) { e.printStackTrace(); }
    }

}
