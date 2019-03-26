import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setupBrowser() {
        System.out.println("setupBrowser :" + Thread.currentThread().getId());
       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\vchmel\\programs\\WebDriverChrome73\\chromedriver.exe");
        System.setProperty("webdriver.firefox.driver", "C:\\Users\\vchmel\\programs\\WebDriverGeckoFirefox\\geckodriver.exe");
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
    }

}
