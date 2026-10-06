package tests;

import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {

    List<String> goodList = List.of(
            "Sauce Labs Fleece Jacket",
            "Test.allTheThings() T-Shirt (Red)",
            "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        loginPage.openPage();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        goodList.forEach(productsPage::addGoodsToCart);
        productsPage.addGoodsToCart(0);

        assertEquals(productsPage.checkCountersValue(), "4");
        assertEquals(productsPage.checkCountersColor(), "rgba(226, 35, 26, 1)");
    }
}