package utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.manager.SeleniumManagerOutput;
import org.testng.ITestContext;

import java.util.Optional;

public class DriverFactory {

    public static WebDriver createDriver(ITestContext context) {
        // Retrieve the browser parameter from the TestNG XML, or 'chrome' if none
        String browser = Optional.ofNullable(context.getCurrentXmlTest().getParameter("browser")).orElse("chrome");
        // Retrieve the browser version parameter from the TestNG XML, or 'stable' if none
        String browserVersion = Optional.ofNullable(context.getCurrentXmlTest().getParameter("browserVersion")).orElse("stable");

        Capabilities caps = null;
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                caps = new ChromeOptions();
                if (browserVersion != null && !browserVersion.isEmpty()) {
                    ((ChromeOptions)caps).setBrowserVersion(browserVersion);
                }
                // Set any other desired capabilities for Chrome
                driver = new ChromeDriver((ChromeOptions) caps);
                break;
            case "firefox":
                caps = new FirefoxOptions();
                if (browserVersion != null && !browserVersion.isEmpty()) {
                    ((FirefoxOptions)caps).setBrowserVersion(browserVersion);
                }
                // Set any other desired capabilities for Firefox
                driver = new FirefoxDriver((FirefoxOptions) caps);
                break;
            case "edge":
                caps = new EdgeOptions();
                if (browserVersion != null && !browserVersion.isEmpty()) {
                    ((EdgeOptions)caps).setBrowserVersion(browserVersion);
                }
                // Set any other desired capabilities for Edge
                driver = new EdgeDriver((EdgeOptions) caps);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");
        }

        SeleniumManagerOutput.Result result = SeleniumManager.getInstance().getDriverPath(caps,false);
        System.out.println("Driver Path: " + result.getDriverPath());
        System.out.println("Browser Path: " + result.getBrowserPath());

        return driver;
    }
}
