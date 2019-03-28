import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class BaseTest {

    public void setupBrowser(String platform, String browser) {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;

        /* Run local browsers */
/*
        Configuration.browser = "firefox";
*/

        /* Run "remote" on local Selenium Grid */
        // 1) java
        //      -jar ./selenium-server-standalone-3.141.59.jar
        //      -host 192.168.56.1
        //      -role hub
        // 2) setup geckodriver into C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe
        //    setup chromedriver into C:\Users\vchmel\programs\WebDriverChrome73\chromedriver.exe
        //
        //  Node must know where its drivers exist!!!
        // 3) Run on the same machine as hub:
        // java
        //      -Dwebdriver.gecko.driver="C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe"
        //      - jar./selenium-server-standalone-3.141.59.jar
        //      -role node
        //      -hub http://192.168.56.1:4444/grid/register/
        // If you have both drivers:
        // $ java
        //      -Dwebdriver.gecko.driver="C:\Users\vchmel\programs\WebDriverGeckoFirefox\geckodriver.exe"
        //      -Dwebdriver.chrome.driver="C:\Users\vchmel\programs\WebDriverChrome73\chromedriver.exe"
        //      - jar./selenium-server-standalone-3.141.59.jar
        //      -role node
        //      -hub  http://192.168.56.1:4444/grid/register/

        DesiredCapabilities dc =  new DesiredCapabilities();
        dc.setPlatform(Platform.fromString(platform));
        dc.setBrowserName(browser);

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.41:4444/wd/hub"),
                    dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriverRunner.setWebDriver(driver);

    }

    @AfterMethod
    public void closeBrowser(){
        WebDriverRunner.closeWebDriver();
    }

}
