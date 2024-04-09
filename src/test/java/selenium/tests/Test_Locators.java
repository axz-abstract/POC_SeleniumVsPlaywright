package selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.pages.LaptopsPage;
import utils.WebDriverUtils;

import java.util.List;

public class Test_Locators extends BaseTest {

    @BeforeClass
    public void PrintName(){
        System.out.println("Locators Suite");
    }

    @Test
    public void SelectCartOfParticularProductCSSMethod(){
        LaptopsPage lapPage = topbarPage.goToShowAllLaptops();
        List<WebElement> products = lapPage.getProducts();
        WebElement spanCart = null;
        for (WebElement aux_prod: products) {
            String name = aux_prod.findElement(lapPage.TTL4Product).getText();
            if (name.equals("MacBook Air")){
                spanCart = aux_prod.findElement(lapPage.spanAddToCart);
                break;
            }
        }
        Assert.assertNotNull(spanCart,"No Cart button could be found related to the product MacBook");
        WebDriverUtils.highlight(spanCart,driver);
    }

    @Test
    public void SelectCartOfParticularProductXPathMethod(){
        topbarPage.goToShowAllLaptops();
        String productName = "MacBook Air";
        String XPathLocator = String.format(
                "//a[normalize-space()='%s']//ancestor::div[@class='product-thumb']//button[contains(@onclick,'cart')]/span",
                productName
        );
        WebElement spanCart = driver.findElement(By.xpath(XPathLocator));
        WebDriverUtils.highlight(spanCart,driver);
    }

    @Test
    public void SelectCartOfParticularProductRelativeLocators(){
        LaptopsPage lapPage = topbarPage.goToShowAllLaptops();
        WebElement mainImg = driver.findElement(lapPage.getPageLoadedLocator());
        By title = By.tagName("h2"), description = By.tagName("p");
        //Highlight title, description and top bar categories
        WebDriverUtils.highlight(driver.findElement(RelativeLocator.with(title).above(mainImg)),driver);
        WebDriverUtils.highlight(driver.findElement(RelativeLocator.with(description).toRightOf(mainImg)),driver);
        WebDriverUtils.highlightAll(
                driver.findElements(RelativeLocator.with(topbarPage.itemCategories).above(mainImg).toLeftOf(mainImg)),
                driver
        );
        //Select cart of product
        WebElement cartButton = driver.findElement(RelativeLocator.with(lapPage.spanAddToCart)
                .below(By.linkText("MacBook Air"))
        );
        WebDriverUtils.highlight(cartButton,driver);
    }

}
