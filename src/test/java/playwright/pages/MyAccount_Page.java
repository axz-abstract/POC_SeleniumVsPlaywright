package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MyAccount_Page extends Base_Page{

    // Locators
    Locator btnNewsletter = page.locator("#column-right").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Newsletter"));
    public Locator alertSuccess = page.getByText("Success");

    public MyAccount_Page(Page page) {
        super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.locator("#content").getByText("My Account");
    }

    public Newsletter_Page goToNewsletter(){
        btnNewsletter.click();
        return new Newsletter_Page(page);
    }

}
