package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public void addGoodsToCart(String goodsName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public void addGoodsToCart(int goodsIndex) {
        By addToCartBtn = By.xpath("//button[text()='Add to cart']");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(addToCartBtn, goodsIndex))
                .get(goodsIndex).click();
    }

    public String checkCountersValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }

    public String checkCountersColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge))
                .getCssValue("background-color");
    }

    public void switchToCart() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        try {
            cart.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
        }
        wait.until(ExpectedConditions.urlContains("cart"));
    }
}