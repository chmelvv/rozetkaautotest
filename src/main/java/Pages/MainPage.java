package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    SelenideElement loginLink = $("a.header-topline__user-link");
    SelenideElement userNameField  = $("#auth_email");
    SelenideElement userPasswordField  = $("#auth_password");
    SelenideElement userLoginButton = $("button.auth-modal__login-button");

    public MainPage open(){
        Selenide.open("/");
        return this;
    }

    public UserProfilePage loginUser(String userMail, String userPassword){
        loginLink.click();
        userNameField.setValue(userMail);
        userPasswordField.setValue(userPassword);
        userLoginButton.click();

        return new UserProfilePage();
    }
}
