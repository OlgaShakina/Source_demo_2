package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage overviewPage;
    CheckoutCompletePage completePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--incognito");
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new CheckoutOverviewPage(driver);
        completePage = new CheckoutCompletePage(driver);
    }

    @Step("Закрытие браузера")

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}