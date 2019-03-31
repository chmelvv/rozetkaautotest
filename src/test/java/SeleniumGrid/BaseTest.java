package SeleniumGrid;

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

        /* Run "remote" on local Selenium Grid */
        // 1) java
        //      -jar ./selenium-server-standalone-3.141.59.jar
        //      -host <HUB_IP>
        //      -role hub
        // 2) setup geckodriver into <FULL_PATH_TO_GECKODRIVER>\geckodriver.exe
        //    setup chromedriver into <FULL_PATH_TO_CHROMEDRIVER>\chromedriver.exe
        //
        //  Node must know where its drivers exist!!!
        // Hub and nodes must be able to access each other
        //      - ping each other,
        //      - from node you must access in browser http://<HUB_IP>:4444/ - default Selenium grid hub site or Console - http://<HUB_IP>:4444/grid/console
        //
        // 3) Run on node machine as hub:
        // java
        //      -Dwebdriver.gecko.driver="<FULL_PATH_TO_GECKODRIVER>\geckodriver.exe"
        //      - jar./selenium-server-standalone-3.141.59.jar
        //      -role node
        //      -hub http://<HUB_IP>:4444/grid/register/
        
        // If you have both drivers:
        // $ java
        //      -Dwebdriver.gecko.driver="<FULL_PATH_TO_GECKODRIVER>\geckodriver.exe"
        //      -Dwebdriver.chrome.driver="<FULL_PATH_TO_CHROMEDRIVER>\chromedriver.exe"
        //      - jar./selenium-server-standalone-3.141.59.jar
        //      -role node
        //      -hub  http://<HUB_IP>:4444/grid/register/

        DesiredCapabilities dc =  new DesiredCapabilities();
        dc.setPlatform(Platform.fromString(platform));
        dc.setBrowserName(browser);

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    new URL("http://192.168.1.40:4444/wd/hub"),
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
