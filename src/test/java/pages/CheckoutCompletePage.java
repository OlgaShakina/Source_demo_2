package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By completeHeader = By.className("complete-header");
    private final By backHomeButton = By.id("back-to-products");
    private final By generatePdfButton = By.id("generate-pdf-order");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public boolean isBackHomeButtonVisible() {
        return driver.findElement(backHomeButton).isDisplayed();
    }

    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}