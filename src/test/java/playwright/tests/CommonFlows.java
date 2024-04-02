package playwright.tests;

import com.microsoft.playwright.Page;
import playwright.pages.Login_Page;
import playwright.pages.MyAccount_Page;
import playwright.pages.NavigationBar_Page;
import playwright.pages.Newsletter_Page;

import java.util.Map;

public class CommonFlows {

    public static MyAccount_Page Login(Page mainPage, Map<String, Object> parameters){
        Login_Page loginPage = new NavigationBar_Page(mainPage).goToLoginPage();
        loginPage.EnterEmail((String) parameters.get("user"));
        loginPage.EnterPassword((String) parameters.get("pass"));
        return loginPage.ClickLogin();
    }

    public static Newsletter_Page goToNewsletterPage(Page mainPage, Map<String, Object> parameters){
        if (parameters!=null)
            return CommonFlows.Login(mainPage,parameters).goToNewsletter();
        else
            return new NavigationBar_Page(mainPage).goToMyAccount().goToNewsletter();
    }

    public static NavigationBar_Page goToHomePage(Page mainPage){
        return new NavigationBar_Page(mainPage).clickLogo();
    }

}
