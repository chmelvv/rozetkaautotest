import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vchmel\\programs\\WebDriverChrome73\\chromedriver.exe");

        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
        //Configuration.holdBrowserOpen = true;
    }
}
