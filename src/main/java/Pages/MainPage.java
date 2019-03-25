package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    SelenideElement loginLink = $("a.header-user-link");
    SelenideElement userNameField  = $("input[name='login']");
    SelenideElement userPasswordField  = $("input[name='password']");
    SelenideElement userLoginButton = $("button[name='auth_submit']");

    public MainPage open(){
        Selenide.open("/");
        Selenide.clearBrowserCookies();
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
