package playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.util.concurrent.TimeUnit;

public abstract class Base_Page {

	protected Page page;

	public Base_Page(Page page) {
		this.page = page;
		isLoaded();
	}

	public abstract Locator getPageLoadedLocator();

	public final void isLoaded() throws PlaywrightException {
		if(!isElementVisible(this.getPageLoadedLocator())) {
			throw new PlaywrightException(String.format("The Page Object could not be found: %s - Using selector: %s", this.getClass().getName(), this.getPageLoadedLocator().toString()) );
		}
	}

	private boolean isElementVisible(Locator pageLoadedLocator) {
		try{
			pageLoadedLocator.waitFor(new Locator.WaitForOptions()
					.setState(WaitForSelectorState.VISIBLE)
					.setTimeout(TimeUnit.SECONDS.toMillis(30))
			);
			return true;
		} catch (Exception e){
			return false;
		}
	}



	
	
	 
	
	
	
	
}
