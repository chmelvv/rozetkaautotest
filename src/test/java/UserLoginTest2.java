import Pages.MainPage;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
public class UserLoginTest2 extends BaseTestLocal {

    private String username;
    private String email;
    private String password;

    @AfterMethod
    public  void logOff(){
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void defaultUserCanLogin(){
        User defaultUser = new User();
        MainPage mainPage = new MainPage();

        mainPage
                .open()
                .loginUser(defaultUser.getUserEmail(), defaultUser.getUserPassword())
                .checkLoggedUserName(defaultUser.getUserName());
    }

    @DataProvider(name = "userList", parallel = true)
    public Object[][] createData1() {
        //read from file
        //CSV DataProvider
        return new Object[][] {
                { "testUser","pizejeku-1795@yopmail.com","kV8AkL4nrC9CbTa"},
                {"emeppubi-4889","emeppubi-4889@yopmail.com","vhLYNfpTpasMZ6b" }
        };
    }

    @Test (dataProvider = "userList")
    public void checkUserListForLogin(String username, String email, String password){
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
