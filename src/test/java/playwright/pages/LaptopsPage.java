package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.Constants;

import java.time.Duration;
import java.util.List;

public class LaptopsPage extends Base_Page{

    public Locator cardProduct = page.locator("#content .product-thumb");

    public LaptopsPage(Page page) {
        super(page);
    }

    @Override
    public Locator getPageLoadedLocator() {
        return page.locator("ul.breadcrumb").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Laptops & Notebooks"));
    }

    public List<Locator> getProducts(){
        page.waitForCondition(
            () -> cardProduct.all().size() == 5,
            new Page.WaitForConditionOptions().setTimeout(Duration.ofSeconds(Constants.TINY_TIMEOUT).toMillis())
        );
        List<Locator> auxList = cardProduct.all();
        for (Locator card : auxList)
            card.waitFor(
                    new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
            );
        return auxList;
    }
}
