package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NavigationBar_Page extends Base_Page{

	// Locator
	private final Locator dropMyAccount = page.locator("#top-links").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("My Account"));
	private final Locator linkLogin = page.locator(".dropdown.open").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Login"));
	private final Locator linkMyAccount = page.locator(".dropdown-menu").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("My Account"));
	private final Locator inputSearch = page.getByPlaceholder("Search");
	private final Locator btnSearch = page.locator("#search").getByRole(AriaRole.BUTTON);
	private final Locator btnLaptops = page.getByText("Laptops & Notebooks").first();
	private final Locator btnShowAllLaptops = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Show All Laptops & Notebooks"));
	private final Locator linkLogo = page.locator("#logo a");

	public NavigationBar_Page(Page page) {
		super(page);
	}

	@Override
	public Locator getPageLoadedLocator() {
		return page.locator("#menu");
	}

	public Login_Page goToLoginPage(){
		dropMyAccount.click();
		linkLogin.click();
		return new Login_Page(page);
	}

	public SearchResultsPage SearchProduct(String product){
		inputSearch.fill(product);
		btnSearch.click();
		return new SearchResultsPage(page);
	}

	public LaptopsPage goToShowAllLaptops(){
		btnLaptops.hover(new Locator.HoverOptions().setForce(true));
		btnShowAllLaptops.click();
		return new LaptopsPage(page);
	}

	public MyAccount_Page goToMyAccount(){
		dropMyAccount.click();
		linkMyAccount.click();
		return new MyAccount_Page(page);
	}

	public NavigationBar_Page clickLogo(){
		linkLogo.click();
		return new NavigationBar_Page(page);
	}

}
	

