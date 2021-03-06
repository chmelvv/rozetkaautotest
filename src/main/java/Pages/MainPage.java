package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class MainPage {

    SelenideElement loginLink = $("a.header-topline__user-link"); //$("a[name='signin']");
    SelenideElement userNameField  = $("input#auth_email"); //$("input[name='login']");
    SelenideElement userPasswordField  = $("input#auth_pass"); //$("input[name='password']");
    SelenideElement userLoginButton = $("button.auth-modal__login-button"); //$("button[name='auth_submit']");
    SelenideElement searchBar = $("input.rz-header-search-input-text");

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


    public ProductDetailsPage search(String productName) {
        searchBar.setValue(productName).pressEnter();

        return new ProductDetailsPage();
    }
}
