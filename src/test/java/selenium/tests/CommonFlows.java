package selenium.tests;
import org.openqa.selenium.WebDriver;
import selenium.pages.*;

import java.util.Map;

public class CommonFlows {

    public static MyAccountPage Login(WebDriver driver, Map<String, Object> parameters){
        String user = (String) parameters.get("user"), pass = (String) parameters.get("pass");
        return new TopBar(driver).goToLoginPage().login(user,pass);
    }

    public static NewsletterPage FlowGoToNewsLetter(WebDriver driver, Map<String, Object> parameters){
        if (parameters != null)
            return CommonFlows.Login(driver,parameters).goToNewsletter();
        else
            return new TopBar(driver).goToMyAccount().goToNewsletter();
    }

    public static TopBar goToHomePage(WebDriver driver){
        TopBar topBar = new TopBar(driver);
        topBar.goToHomePage();
        return topBar;
    }

    public static ShoppingCartPage goToShoppingCart(WebDriver driver,Map<String, Object> parameters){
        if (parameters != null)
            CommonFlows.Login(driver, parameters);
        return new TopBar(driver).goToShoppingCart();
    }

}
