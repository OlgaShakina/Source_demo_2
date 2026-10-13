package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

import static pages.BasePage.BASE_URL;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем соответствующий браузер")
    public void open() {
        driver.get(BASE_URL);
    }

    public void open(String url) {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под кредами пользователя")
    public void login(User user) {
        fillLoginInput(user.getUser());
        fillPasswordInput(user.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    @Step("Заполняем поле логина {user}")
    public void fillLoginInput(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(user);
    }

    @Step("Заполняем поле пароля {password}")
    public void fillPasswordInput(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Проверяем, что сообщение об ошибке отображается")
    public boolean isErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed();
    }

    @Step("Проверяем, что текст сообщения об ошибке")
    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
    }
}