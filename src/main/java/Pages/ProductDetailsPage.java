package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProductDetailsPage {

    SelenideElement productId = $("span.detail-code-i");

    public ProductDetailsPage checkIsProductFound(String productId) {

        this.productId.shouldHave(text(productId));

        return this;
    }
}
