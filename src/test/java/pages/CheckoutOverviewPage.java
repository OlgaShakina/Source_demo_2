package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private final SelenideElement finishBtn     = $("#finish");
    private final SelenideElement cancelBtn     = $("#cancel");
    private final ElementsCollection cartItems  = $$(".cart_item");
    private final ElementsCollection itemNames  = $$(".inventory_item_name");
    private final ElementsCollection itemPrices = $$(".inventory_item_price");

    @Step("Нажимаем Finish")
    public void clickFinish() {
        finishBtn.click();
    }

    @Step("Проверяем, что список товаров отображается")
    public boolean isProductListDisplayed() {
        return !cartItems.isEmpty();
    }

    @Step("Получаем названия товаров на обзоре")
    public List<String> getProductNames() {
        return itemNames.texts();
    }

    @Step("Проверяем, что кнопка Finish видна")
    public boolean isFinishButtonVisible() {
        return finishBtn.is(visible);
    }
}