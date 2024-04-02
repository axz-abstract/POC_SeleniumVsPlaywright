package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductPage extends Base_Page {

    Locator headingProdName = page.locator("#content").getByRole(AriaRole.HEADING).first();

    public ProductPage(Page page) {
        super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.locator("#product");
    }

    public Locator getProductName(){
        return headingProdName;
    }
}
