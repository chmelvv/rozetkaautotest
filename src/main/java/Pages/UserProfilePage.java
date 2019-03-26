package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class UserProfilePage {

    SelenideElement userProfileLink = $("a.header-topline__user-link"); // $("a[name='profile']");

    public UserProfilePage checkLoggedUserName(String expUserName){
        userProfileLink.shouldHave(Condition.text(expUserName));
        return this;
    }
}
