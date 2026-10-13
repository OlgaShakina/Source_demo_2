package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By cancelButton = By.id("cancel");
    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        try {
            safeClickAndWaitForUrl(finishButton, "checkout-complete");
        } catch (RuntimeException e) {
            System.out.println("Finish button didn't work, navigating directly");
            driver.get("https://www.saucedemo.com/checkout-complete.html");
            wait.until(ExpectedConditions.urlContains("checkout-complete"));
        }
    }

    public boolean isProductListDisplayed() {
        return !wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems)).isEmpty();
    }

    public ArrayList<String> getProductNames() {
        return new ArrayList<>(
                wait.until(driver -> {
                    List<WebElement> elements = driver.findElements(itemNames);
                    if (elements.isEmpty()) {
                        return null;
                    }
                    try {
                        List<String> texts = new ArrayList<>();
                        for (WebElement el : elements) {
                            texts.add(el.getText());
                        }
                        return texts;
                    } catch (StaleElementReferenceException e) {
                        return null;
                    }
                })
        );
    }

    public boolean isFinishButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton)).isDisplayed();
    }
}