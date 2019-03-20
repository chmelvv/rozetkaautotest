package Pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class UserProfilePage {

    public void checkLoggedUserName(String expUserName){
        $("a.header-topline__user-link").shouldHave(Condition.text(expUserName));
    }
}
