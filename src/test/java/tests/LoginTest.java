package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import pages.ProductsPage;

@Epic("Авторизация")
@Feature("Успешный вход в систему")
@Owner("Olga, olga.buinova@gmail.com")
public class LoginTest extends BaseTest {

    @DataProvider(name = "oioio")
    public Object[][] loginData() {
        return new Object[][]{
                {withAdminPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {new User("standard_user", "secret_sauce"),
                        "Epic sadface: Username and password do not match any user in this service"},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"}
        };
    }

    @Story("Проверка ошибок при некорректных данных")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "oioio", priority = 1, enabled = false)
    public void incorrectDataLoginTest(User user, String errorMsg) {
        loginPage.openPage();
        loginPage.login(user);

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), errorMsg, "Error text does not match");
    }

    @Test(description = "Проверка авторизации", priority = 2)
    public void correctUserTest() {
        loginPage.openPage();
        ProductsPage products = loginPage.login(withAdminPermission());

        assertTrue(products.isPageTitleVisible(), "Заголовок Products не отображается");
        assertEquals(products.getPageTitle(), PRODUCTS.getDisplayName(),
                "Заголовок страницы не совпадает");
    }
}