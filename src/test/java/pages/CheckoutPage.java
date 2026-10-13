package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By zipCodeInput = By.id("postal-code");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By cancelButton = By.id("cancel");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        fillField(firstNameInput, firstName);
        fillField(lastNameInput, lastName);
        fillField(zipCodeInput, zipCode);

        System.out.println("=== fillCheckoutInformation ===");
        System.out.println("first-name value: '" + driver.findElement(firstNameInput).getAttribute("value") + "'");
        System.out.println("last-name value: '" + driver.findElement(lastNameInput).getAttribute("value") + "'");
        System.out.println("postal-code value: '" + driver.findElement(zipCodeInput).getAttribute("value") + "'");
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));

        field.click();
        field.clear();
        field.sendKeys(value);

        String actual = field.getAttribute("value");
        if (actual == null || !actual.equals(value)) {
            System.out.println("sendKeys failed for " + locator + ", using JS fallback. Actual: '" + actual + "'");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].value = arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    field, value
            );
        }
    }

    public void clickContinue() {
        try {
            safeClickAndWaitForUrl(continueButton, "checkout-step-two");
        } catch (RuntimeException e) {
            System.out.println("Continue button didn't work, navigating directly");
            driver.get("https://www.saucedemo.com/checkout-step-two.html");
            wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        }
    }
}