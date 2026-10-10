package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

public class CartPage extends BasePage {

    private final SelenideElement continueShopping = $("#continue-shopping");
    private final SelenideElement checkoutButton   = $("#checkout");
    private final ElementsCollection productsNames = $$(".inventory_item_name");

    @Step("Получаем названия товаров в корзине")
    public List<String> getProductsNames() {
        continueShopping.shouldBe(visible);
        return productsNames.texts();
    }

    @Step("Нажимаем Checkout")
    public void clickCheckout() {
        checkoutButton.click();
    }
}