package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final String BASE_URL = PropertyReader.getProperty("saucedemmo.url");
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    protected void safeClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    protected void safeClickAndWaitForUrl(By locator, String urlPart) {
        for (int i = 0; i < 3; i++) {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception e) {
                el.click();
            }
            try {
                wait.until(ExpectedConditions.urlContains(urlPart));
                return;
            } catch (org.openqa.selenium.TimeoutException ignored) {
                System.out.println("safeClick attempt " + (i + 1)
                        + " failed for URL: " + urlPart
                        + ". Current URL: " + driver.getCurrentUrl());
            }
        }
        throw new RuntimeException("Не удалось кликнуть и дождаться URL: " + urlPart);
    }
}