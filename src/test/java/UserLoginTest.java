import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserLoginTest {

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vchmel\\programs\\WebDriverChrome73\\chromedriver.exe");
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;

        Selenide.clearBrowserCookies(); //to clear login
    }

    @Test
    public static void userCanLogin(){

        open("/");

        $("a.header-topline__user-link").click();
        $("#auth_email").setValue("pizejeku-1795@yopmail.com");
        $("#auth_pass").setValue("kV8AkL4nrC9CbTa");
        $("button.auth-modal__login-button").click();

        $("a.header-topline__user-link").shouldHave(Condition.text( "testUser"));
    }
}
