package playwright.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import playwright.pages.LaptopsPage;
import playwright.pages.NavigationBar_Page;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test_Locators extends BaseTest{

    String productName = "MacBook Air";

    @Test
    public void SelectCartOfParticularProductLoopThroughArray(){
        LaptopsPage laptopsPage = new NavigationBar_Page(mainPage).goToShowAllLaptops();
        List<Locator> products = laptopsPage.getProducts();
        assertThat(laptopsPage.cardProduct).hasCount(5);
        Locator spanCart = null;
        for (Locator card: products) {
            String auxName = card.getByRole(AriaRole.HEADING).textContent();
            if (auxName.equals(productName)){
                spanCart = card.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add To Cart"));
                break;
            }
        }
        Assert.assertNotNull(spanCart,"No Cart button could be found related to the product MacBook");
        spanCart.scrollIntoViewIfNeeded();
        spanCart.highlight();
        mainPage.waitForTimeout(2000);
    }

    @Test
    public void SelectCartOfParticularProductXPathMethod(){
        LaptopsPage laptopsPage = new NavigationBar_Page(mainPage).goToShowAllLaptops();
        String XPathLocator = String.format(
                "//a[normalize-space()='%s']//ancestor::div[@class='product-thumb']//button[contains(@onclick,'cart')]/span",
                productName
        );
        Locator spanCart = mainPage.locator(XPathLocator);
        spanCart.scrollIntoViewIfNeeded();
        spanCart.highlight();
        mainPage.waitForTimeout(2000);
    }

    @Test
    public void SelectCartOfParticularProductPlaywrightFilter(){
        LaptopsPage laptopsPage = new NavigationBar_Page(mainPage).goToShowAllLaptops();
        Locator prodNameElement = this.mainPage.getByText(productName);
        Locator parentElement = laptopsPage.cardProduct.filter(new Locator.FilterOptions().setHas(prodNameElement));
        parentElement.scrollIntoViewIfNeeded();
        parentElement.highlight();
        parentElement.getByRole(AriaRole.BUTTON).first().highlight();
        mainPage.waitForTimeout(2000);
        parentElement.getByRole(AriaRole.BUTTON).nth(2).highlight();
        mainPage.waitForTimeout(2000);
        assertThat(parentElement).isVisible();
    }

}
