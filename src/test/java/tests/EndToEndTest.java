package tests;

import enums.TitleNaming;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class EndToEndTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Test(description = "Полный цикл покупки: Корзина -> Checkout -> Overview -> Complete")
    public void checkFullCheckoutFlow() {
        List<String> goodList = List.of("Sauce Labs Bike Light", "Sauce Labs Backpack");

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodList) {
            productsPage.addGoodsToCart(goodsName);
        }

        productsPage.switchToCart();
        assertEquals(cartPage.getProductsNames().size(), goodList.size(), "Товары не добавились в корзину!");

        cartPage.clickCheckout();

        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();

        assertTrue(overviewPage.isFinishButtonVisible(), "Кнопка Finish не видна!");

        soft.assertTrue(overviewPage.isProductListDisplayed(), "Список товаров на странице обзора пуст!");
        soft.assertEquals(overviewPage.getProductNames().size(), goodList.size(), "Количество товаров в обзоре не совпадает!");

        overviewPage.clickFinish();

        String expectedHeader = "Thank you for your order!";
        assertEquals(completePage.getCompleteHeaderText(), expectedHeader, "Текст благодарности не совпадает!");

        assertTrue(completePage.isBackHomeButtonVisible(), "Кнопка Back Home не отображается!");

        completePage.clickBackHome();
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName(), "Не вернулись на главную страницу!");

        soft.assertAll();
    }
}