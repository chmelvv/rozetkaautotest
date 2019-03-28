import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTestLocal {

    @BeforeMethod
    public void setupBrowser() {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
        Configuration.browserVersion = "66";
    }

}
