package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {

    SoftAssert soft = new SoftAssert();

    @Test
    public void checkGoodsInCart() {
        List<String> goodList = List.of(
                "Sauce Labs Fleece Jacket",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Bolt T-Shirt");

        loginPage.openPage();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        goodList.forEach(productsPage::addGoodsToCart);
        productsPage.switchToCart();

        soft.assertFalse(cartPage.getProductsNames().isEmpty(), "Корзина пуста!");
        soft.assertEquals(cartPage.getProductsNames().size(), goodList.size());

        soft.assertAll();
    }
}