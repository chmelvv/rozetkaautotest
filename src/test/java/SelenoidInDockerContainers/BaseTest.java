package SelenoidInDockerContainers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;

@Slf4j
public class BaseTest {

    @BeforeClass
    public void setupBrowser(ITestContext context) throws MalformedURLException {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
        DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName( context.getCurrentXmlTest().getParameter("browser") );
            browser.setVersion( context.getCurrentXmlTest().getParameter("browserVersion") );
           // browser.setCapability("enableVNC", "true" );
        RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://10.124.89.109:8001/wd/hub").toURL(), browser);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass
    public void close(){
        WebDriverRunner.closeWebDriver();
    }





}
