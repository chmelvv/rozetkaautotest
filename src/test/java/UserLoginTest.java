import Pages.MainPage;
import Pages.UserProfilePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserLoginTest {

    MainPage mainPage = new MainPage();
    UserProfilePage userProfilePage = new UserProfilePage();

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vchmel\\programs\\WebDriverChrome73\\chromedriver.exe");

        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;

        Selenide.clearBrowserCookies(); //to clear login
    }

    @Test
    public void userCanLogin(){
        mainPage
                .open()
                .loginUser("pizejeku-1795@yopmail.com", "kV8AkL4nrC9CbTa")
                .checkLoggedUserName("testUser");
    }
}
