package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class BasePage.
 * Parent class of all Page Objects, has the common elements that appear on screen as well as methods that the derived class need to use 
 */
public abstract class BasePage {
	
	/** The driver is used mainly to find elements on the present Page Object, but it also can be used to implement waits or Javascript executions. */
	public WebDriver driver;
	
	/**
	 * The basic Page Object constructor 
	 * It waits until the element finder in the method is found or the timer has finished.
	 * On the second case a Timeout Exception is thrown
	 * @param driver the driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.isLoaded();
	}
	
	/**
	 * Checks if the page object is loaded with the locator provided by the getPageLoadedLocator method.
	 * @throws WebDriverException the illegal access exception
	 */
	public final void isLoaded() throws WebDriverException {
		if(!isElementPresent(this.getPageLoadedLocator())) {
			throw new WebDriverException("This is not " + this.getClass().getName() + " Page Object");
		}	
	}

	public abstract By getPageLoadedLocator();

	public boolean isElementPresent(final By locator) {
		try {
			this.ExplicitWaitElement(
					ExpectedConditions.visibilityOfElementLocated(locator),
					Constants.MEDIUM_TIMEOUT
			);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement ExplicitWaitElement(ExpectedCondition<WebElement> expectedCondition, int seconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(expectedCondition);
	}

	public void ExplicitWaitFrame(ExpectedCondition<WebDriver> expectedCondition, int seconds) {
		new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(expectedCondition);
	}

	public void ExplicitWaitAlert(ExpectedCondition<Alert> expectedCondition, int seconds) {
		new WebDriverWait(driver,Duration.ofSeconds(seconds)).until(expectedCondition) ;
	}

	public void ExplicitWaitBoolean(ExpectedCondition<Boolean> expectedCondition, int seconds) {
		new WebDriverWait(driver,Duration.ofSeconds(seconds)).until(expectedCondition) ;
	}

	public  List<WebElement> ExplicitWaitListElement(ExpectedCondition<List<WebElement>> expectedCondition, int seconds) {
		return new WebDriverWait(driver,Duration.ofSeconds(seconds)).until(expectedCondition);
	}

	public void ImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	/**
	 * Input data.
	 * auxiliary method to erase, send data and set the focus away
	 * @param e the input
	 * @param data the data to send
	 */
	public void InputData(WebElement e, String data, boolean tab) {
		e.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		e.sendKeys(data);
		if (tab)
			e.sendKeys(Keys.TAB);
	}

}
