package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UserProfilePage {

    SelenideElement userProfileLink = $("a[name='profile']");

    public void checkLoggedUserName(String expUserName){
        userProfileLink.shouldHave(Condition.text(expUserName));
    }
}
