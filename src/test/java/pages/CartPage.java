package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShopping));
        List<WebElement> allproducts = driver.findElements(productsNames);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allproducts) {
            names.add(product.getText());
        }
        return names;
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}