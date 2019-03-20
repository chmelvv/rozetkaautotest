package Pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public MainPage open(){
        Selenide.open("/");
        return this;
    }

    public UserProfilePage loginUser(String userMail, String userPassword){
        $("a.header-topline__user-link").click();
        $("#auth_email").setValue(userMail);
        $("#auth_pass").setValue(userPassword);
        $("button.auth-modal__login-button").click();

        return new UserProfilePage();
    }
}
