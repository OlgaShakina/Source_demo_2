package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By continueShopping = By.id("continue-shopping");
    private final By productsNames = By.cssSelector(".inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsNames() {
        return new ArrayList<>(
                wait.until(driver -> {
                    List<WebElement> elements = driver.findElements(productsNames);
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

    public void clickCheckout() {
        safeClickAndWaitForUrl(checkoutButton, "checkout-step-one");
    }
}