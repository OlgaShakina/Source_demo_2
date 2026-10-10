package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class EndToEndTest extends BaseTest {

    @Test(description = "Полный цикл покупки: Cart -> Checkout -> Overview -> Complete")
    public void checkFullCheckoutFlow() {
        SoftAssert soft = new SoftAssert();
        List<String> goodList = List.of("Sauce Labs Bike Light", "Sauce Labs Backpack");

        loginPage.openPage();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageTitleVisible(), "Заголовок Products не виден");
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName(),
                "Не попали на страницу Products");

        goodList.forEach(productsPage::addGoodsToCart);
        productsPage.switchToCart();

        soft.assertEquals(cartPage.getProductsNames().size(), goodList.size(),
                "Количество товаров в корзине не совпадает");

        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();

        soft.assertTrue(overviewPage.isFinishButtonVisible(), "Кнопка Finish не видна!");
        soft.assertTrue(overviewPage.isProductListDisplayed(), "Список товаров пуст!");
        soft.assertEquals(overviewPage.getProductNames().size(), goodList.size(),
                "Количество товаров на Overview не совпадает");

        overviewPage.clickFinish();

        soft.assertEquals(completePage.getCompleteHeaderText(), "Thank you for your order!",
                "Заголовок подтверждения заказа не совпадает");
        soft.assertTrue(completePage.isBackHomeButtonVisible(),
                "Кнопка Back Home не видна");

        completePage.clickBackHome();
        soft.assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName(),
                "После возврата не открылась страница Products");

        soft.assertAll();
    }
}