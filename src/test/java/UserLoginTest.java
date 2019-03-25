import Pages.MainPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest{

    MainPage mainPage = new MainPage();

//    @BeforeTest
//    public void logOff(){
//        Selenide.clearBrowserCookies();
//    }

    @Test
    public void defaultUserCanLogin(){
        User defaultUser = new User();

        mainPage
                .open()
                .loginUser(defaultUser.getUserEmail(), defaultUser.getUserPassword())
                .checkLoggedUserName(defaultUser.getUserName());
    }

    //    A Data Provider is a method on your class that returns an array of array of objects.
    @DataProvider(name = "userList")
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
        mainPage
                .open()
                .loginUser(email, password)
                .checkLoggedUserName(username);
    }

    @Test
    public void parametrizedUserCanLogin(String username, String email, String password){
        mainPage
                .open()
                .loginUser(email, password)
                .checkLoggedUserName(username);
    }
}
