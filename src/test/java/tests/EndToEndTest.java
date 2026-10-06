package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class EndToEndTest extends BaseTest {

    SoftAssert soft = new SoftAssert();

    @Test(description = "Полный цикл покупки: Cart -> Checkout -> Overview -> Complete")
    public void checkFullCheckoutFlow() {
        List<String> goodList = List.of("Sauce Labs Bike Light", "Sauce Labs Backpack");

        loginPage.openPage();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        goodList.forEach(productsPage::addGoodsToCart);
        productsPage.switchToCart();
        assertEquals(cartPage.getProductsNames().size(), goodList.size(), "Товары не добавились!");

        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();

        assertTrue(overviewPage.isFinishButtonVisible(), "Кнопка Finish не видна!");
        soft.assertTrue(overviewPage.isProductListDisplayed(), "Список товаров пуст!");
        soft.assertEquals(overviewPage.getProductNames().size(), goodList.size());

        overviewPage.clickFinish();

        assertEquals(completePage.getCompleteHeaderText(), "Thank you for your order!");
        assertTrue(completePage.isBackHomeButtonVisible());

        completePage.clickBackHome();
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        soft.assertAll();
    }
}