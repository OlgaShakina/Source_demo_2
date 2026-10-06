package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage extends BasePage {

    private final SelenideElement firstName = $("#first-name");
    private final SelenideElement lastName  = $("#last-name");
    private final SelenideElement zipCode   = $("#postal-code");
    private final SelenideElement continueBtn = $("#continue");
    private final SelenideElement cancelBtn   = $("#cancel");

    @Step("Заполняем данные: {fName} {lName}, {zip}")
    public void fillCheckoutInformation(String fName, String lName, String zip) {
        firstName.setValue(fName);
        lastName.setValue(lName);
        zipCode.setValue(zip);
    }

    @Step("Нажимаем Continue")
    public void clickContinue() {
        continueBtn.click();
    }

    @Step("Нажимаем Cancel")
    public void clickCancel() {
        cancelBtn.click();
    }
}