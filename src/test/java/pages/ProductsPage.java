package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//*[text()='%s']/" +
            "ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By cartBadge = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }

    public void addGoodsToCart(String goodsName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCartBtn).click();
    }

    public void addGoodsToCart(int goodsIndex) {
        By addToCartBtn = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartBtn).get(goodsIndex).click();
    }

    public String checkCountersValue() {
        return driver.findElement(cartBadge).getText();
    }

    public String checkCountersColor() {
        return driver.findElement(cartBadge).getCssValue("background-color");
    }

    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}