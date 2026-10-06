package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage extends BasePage {

    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement backHomeBtn    = $("#back-to-products");

    @Step("Получаем заголовок благодарности")
    public String getCompleteHeaderText() {
        return completeHeader.shouldBe(visible).getText();
    }

    @Step("Проверяем, что кнопка Back Home видна")
    public boolean isBackHomeButtonVisible() {
        return backHomeBtn.is(visible);
    }

    @Step("Нажимаем Back Home")
    public void clickBackHome() {
        backHomeBtn.click();
    }
}