package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Newsletter_Page extends Base_Page{

    // Locator
    public Locator radiobtnSubscribeYes = page.getByLabel("Yes");
    public Locator radiobtnSubscribeNo = page.getByLabel("No");
    Locator btnContinue = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));

    public Newsletter_Page(Page page) {
        super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.locator("#account-newsletter");
    }

    public MyAccount_Page Subscribe(){
        radiobtnSubscribeYes.check();
        btnContinue.click();
        return new MyAccount_Page(page);
    }

    public MyAccount_Page Unsubscribe(){
        radiobtnSubscribeNo.check();
        btnContinue.click();
        return new MyAccount_Page(page);
    }


}
