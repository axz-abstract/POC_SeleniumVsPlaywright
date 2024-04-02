package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage extends Base_Page {

    // Locators
    Locator title = page.locator("#product-search h1");

    public SearchResultsPage(Page page) {
        super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.locator("#product-search");
    }

    public ProductPage selectProduct(String prodName){
        page.locator(".caption").getByText(prodName).first().click();
        return new ProductPage(page);
    }

    public Locator getTitle(){
        return title;
    }
}
