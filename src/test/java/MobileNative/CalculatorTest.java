package MobileNative;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;

@Log4j
public class CalculatorTest  {

    @Test
    public void testCalculator() throws MalformedURLException {

        Configuration.startMaximized = false;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus_5X_API_26");
        capabilities.setCapability(APP_PACKAGE, "com.android.calculator2");
        capabilities.setCapability(APP_ACTIVITY, "com.android.calculator2.Calculator");
          capabilities.setCapability("udid", "emulator-5554");
        //capabilities.setCapability("udid", "94117ffc");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);

        $(By.id("digit_2")).click();
        $(By.id("op_add")).click();
        $(By.id("digit_2")).click();
        $(By.id("eq")).click();


        $(By.id("result")).shouldHave(Condition.text("3"));
    }

}
