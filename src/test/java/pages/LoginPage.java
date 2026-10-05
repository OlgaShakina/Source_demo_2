package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import user.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    private final SelenideElement usernameInput = $("[data-test='username']");
    private final SelenideElement passwordInput = $("[data-test='password']");
    private final SelenideElement loginBtn      = $("#login-button");
    private final SelenideElement error         = $("h3[data-test='error']");

    @Step("Открываем страницу логина")
    public LoginPage openPage() {
        open();         
        return this;
    }

    @Step("Авторизация под кредами пользователя")
    public ProductsPage login(User user) {
        fillLoginInput(user.getUser());
        fillPasswordInput(user.getPassword());
        loginBtn.click();
        return new ProductsPage();
    }

    @Step("Заполняем поле логина {user}")
    public void fillLoginInput(String user) {
        usernameInput.setValue(user);
    }

    @Step("Заполняем поле пароля")
    public void fillPasswordInput(String password) {
        passwordInput.setValue(password);
    }

    @Step("Проверяем, что сообщение об ошибке отображается")
    public boolean isErrorVisible() {
        return error.is(visible);
    }

    @Step("Получаем текст ошибки")
    public String getErrorText() {
        return error.shouldBe(visible).getText();
    }
}
