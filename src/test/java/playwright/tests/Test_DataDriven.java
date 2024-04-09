package playwright.tests;

import data_providers.DataProviderDefault;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playwright.pages.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test_DataDriven extends BaseTest{

    @BeforeClass
    public void PrintName(){
        System.out.println("Data-driven Suite");
    }

    @Test(dataProvider = "Products",dataProviderClass = DataProviderDefault.class)
    public void localDataProvider(String searchText){
        NavigationBar_Page home = new NavigationBar_Page(mainPage);
        SearchResultsPage resultsPage = home.SearchProduct(searchText);
        assertThat(resultsPage.getTitle()).hasText("Search - " + searchText);
        ProductPage productPage = resultsPage.selectProduct(searchText);
        assertThat(mainPage).hasTitle(Pattern.compile(".*"+searchText+".*"));
        assertThat(productPage.getProductName()).containsText(searchText);
    }

    @Test(dataProvider = "Users", dataProviderClass = DataProviderDefault.class)
    public void TestExternalProvider(String email, String password, String subscribe_param){
        boolean subscribe = Boolean.parseBoolean(subscribe_param);
        NavigationBar_Page home = new NavigationBar_Page(mainPage);
        // Navigate to login page
        Login_Page loginPage = home.goToLoginPage();
        // Enter credentials and submit
        loginPage.EnterEmail(email);
        loginPage.EnterPassword(password);
        MyAccount_Page myAccountPage = loginPage.ClickLogin();
        assertThat(myAccountPage.getPageLoadedLocator()).hasText("My Account");
        Newsletter_Page newsletterPage = myAccountPage.goToNewsletter();
        // Assert that the Newsletter page has the correct title
        assertThat(mainPage).hasTitle("Newsletter Subscription");
        // Subscribe or unsubscribe according to parameter
        myAccountPage = subscribe ? newsletterPage.Subscribe() : newsletterPage.Unsubscribe();
        // Assert success message
        assertThat(myAccountPage.alertSuccess).hasText(" Success: Your newsletter subscription has been successfully updated!");
        newsletterPage = myAccountPage.goToNewsletter();
        // Assert radio button
        if (subscribe)
            assertThat(newsletterPage.radiobtnSubscribeYes).isChecked();
        else
            assertThat(newsletterPage.radiobtnSubscribeNo).isChecked();
    }

}
