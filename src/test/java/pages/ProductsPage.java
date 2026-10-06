package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage {

    private final SelenideElement pageTitle    = $("[data-test='title']");
    private final SelenideElement cartLink     = $(".shopping_cart_link");
    private final SelenideElement cartCounter  = $(".shopping_cart_badge");
    private final ElementsCollection addButtons = $$("button[id^='add-to-cart']");
    private final ElementsCollection itemNames  = $$(".inventory_item_name");

    @Step("Проверяем, что заголовок страницы виден")
    public boolean isPageTitleVisible() {
        pageTitle.shouldBe(visible);
        return true;
    }

    @Step("Получаем заголовок страницы")
    public String getPageTitle() {
        return pageTitle.shouldBe(visible).getText();
    }

    @Step("Добавляем товар '{name}' в корзину")
    public void addGoodsToCart(String name) {
        $$(".inventory_item")
                .findBy(text(name))
                .$("button")
                .click();
    }

    @Step("Добавляем товар по индексу {index} в корзину")
    public void addGoodsToCart(int index) {
        addButtons.get(index).click();
    }

    @Step("Переходим в корзину")
    public void switchToCart() {
        cartLink.click();
    }

    @Step("Получаем значение счётчика корзины")
    public String checkCountersValue() {
        return cartCounter.shouldBe(visible).getText();
    }

    @Step("Получаем цвет счётчика корзины")
    public String checkCountersColor() {
        return cartCounter.getCssValue("background-color");
    }
}