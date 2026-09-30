package tests;

import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodList =
            List.of("Sauce Labs Fleece Jacket",
                    "Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Bolt T-Shirt");

    @Test()
    public void checkGoodsAdded() {
        System.out.println("checkGoodsAdded is running in thread: " + Thread.currentThread().getId());


        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodList) {
            productsPage.addGoodsToCart(goodsName);
        }

        productsPage.addGoodsToCart(0);
        assertEquals(productsPage.checkCountersValue(), "4");
        assertEquals(productsPage.checkCountersColor(), "rgba(226, 35, 26, 1)");
    }
}