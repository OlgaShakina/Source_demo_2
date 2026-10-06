package pages;

import com.codeborne.selenide.Selenide;
import utils.PropertyReader;

public class BasePage {

    public static final String BASE_URL = PropertyReader.getProperty("saucedemmo.url");

    public void open() {
        Selenide.open(BASE_URL);
    }
}