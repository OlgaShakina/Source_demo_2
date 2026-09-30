package tests;

import enums.TitleNaming;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import user.User;
import user.UserFactory;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.AssertJUnit.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Test()
    public void checkGoodsInCart() {
        List<String> goodList =
                List.of("Sauce Labs Fleece Jacket",
                        "Test.allTheThings() T-Shirt (Red)",
                        "Sauce Labs Bolt T-Shirt");
        System.out.println("checkGoodsInCart is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodList) {
            productsPage.addGoodsToCart(goodsName);
        }

        productsPage.switchToCart();

        soft.assertFalse(cartPage.getProductsNames().isEmpty(), "Корзина пуста!");
        soft.assertEquals(goodList.size(), cartPage.getProductsNames().size());

        soft.assertAll();
    }
}