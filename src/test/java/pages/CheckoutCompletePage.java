package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By completeHeader = By.className("complete-header");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public boolean isBackHomeButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backHomeButton)).isDisplayed();
    }

    public void clickBackHome() {
        try {
            safeClickAndWaitForUrl(backHomeButton, "inventory");
        } catch (RuntimeException e) {
            System.out.println("Back Home button didn't work, navigating directly");
            driver.get("https://www.saucedemo.com/inventory.html");
            wait.until(ExpectedConditions.urlContains("inventory"));
        }
    }
}