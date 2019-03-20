import Pages.MainPage;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest{

    MainPage mainPage = new MainPage();

    @Test
    public void userCanLogin(){
        User defaultUser = new User();

        mainPage
                .open()
                .loginUser(defaultUser.getUserEmail(), defaultUser.getUserPassword())
                .checkLoggedUserName(defaultUser.getUserName());
    }
}
