package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");
    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isProductListDisplayed() {
        return !driver.findElements(cartItems).isEmpty();
    }

    public ArrayList<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        List<WebElement> items = driver.findElements(itemNames);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement item : items) {
            names.add(item.getText());
        }
        return names;
    }

    public boolean isFinishButtonVisible() {
        return driver.findElement(finishButton).isDisplayed();
    }
}