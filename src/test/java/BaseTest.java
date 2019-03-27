import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;

@Slf4j
public class BaseTest {

    @BeforeClass
    public void setupBrowser() {
        log.debug("setupBrowser :" + Thread.currentThread().getId());

        Configuration.browserVersion = "66";
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
    }

}
