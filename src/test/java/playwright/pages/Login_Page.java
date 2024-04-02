package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Login_Page extends Base_Page{

    // Locators
    private final Locator inputEmail = page.getByPlaceholder("E-Mail Address");
    private final Locator inputPass = page.getByPlaceholder("Password");
    private final Locator btnLogin = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));

    public Login_Page(Page page) {
    	super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.getByText("Account Login");
    }

    public void EnterEmail(String email){
        inputEmail.focus();
        inputEmail.fill(email);
    }

    public void EnterPassword(String password){
        inputPass.focus();
        inputPass.fill(password);
    }

    public MyAccount_Page ClickLogin(){
        btnLogin.click();
        return new MyAccount_Page(page);
    }
	
}
