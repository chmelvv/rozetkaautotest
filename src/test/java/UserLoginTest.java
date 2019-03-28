import Pages.MainPage;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Slf4j
public class UserLoginTest extends BaseTestLocal {

    /* Initialized from testng.xml */
    private String username;
    private String email;
    private String password;

    @AfterMethod
    public  void logOff(){
       // log.debug("logOff :" + Thread.currentThread().getId());
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void defaultUserCanLogin(){
        log.debug("defaultUserCanLogin :" + Thread.currentThread().getId());
        User defaultUser = new User();
        MainPage mainPage = new MainPage();

        mainPage
                .open()
                .loginUser(defaultUser.getUserEmail(), defaultUser.getUserPassword())
                .checkLoggedUserName(defaultUser.getUserName());
    }

    @DataProvider(name = "userList", parallel = true)
    public Object[][] createData1() {
        return new Object[][] {
                { "testUser","pizejeku-1795@yopmail.com","kV8AkL4nrC9CbTa"},
                {"emeppubi-4889","emeppubi-4889@yopmail.com","vhLYNfpTpasMZ6b" }
        };
    }

    @Test (dataProvider = "userList")
    public void checkUserListForLogin(String username, String email, String password){
        log.info("checkUserListForLogin: " + Thread.currentThread().getId());

        MainPage mainPage = new MainPage();

        mainPage
                .open()
                .loginUser(email, password)
                .checkLoggedUserName(username);
    }

    @Test
    @Parameters ({"username", "email", "password"})
    public void parametrizedUserCanLogin(String username, String email, String password){
        MainPage mainPage = new MainPage();
        mainPage
                .open()
                .loginUser(email, password)
                .checkLoggedUserName(username);
    }
}
