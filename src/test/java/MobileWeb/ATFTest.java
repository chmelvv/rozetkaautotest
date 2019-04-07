package MobileWeb;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ATFTest {

    @Test
    public void LabSelenideTest() {

        Configuration.baseUrl = "https://www.toolsqa.com/automation-practice-form";

        // http://appium.io/docs/en/writing-running-appium/web/mobile-web/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus_5X_API_26");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        AppiumDriver driver = null;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriverRunner.setWebDriver(driver);


        //TODO Открыть тестовую страницу: https://www.toolsqa.com/automation-practice-form
        open("/");

        //TODO: Вывести в консоль текст Инфо сообщения
        System.out.println($(".vc_message_box").getText());

        //TODO: Вывести в консоль текст заголовка формы ("Practice Automation Form")
        System.out.println($("div.wpb_wrapper h1").getText());

        //TODO: Кликнуть линк Partial Link Test
        $(Selectors.byPartialLinkText("Partial")).click();
        // $(Selectors.byText("Partial Link Test")).click(); // Full text needed to assert

        //TODO: Заполнить поле First name:
        $(By.name("firstname")).setValue("My First Name");

        //TODO: Заполнить поле Last name:
        //        WebElement lastName = driver.findElement(By.name("lastname"));
        //        lastName.sendKeys("Last Name");

        $(By.name("lastname")).setValue("Last Name");

        //TODO: Выбрать пол

        //TODO: Выбрать количество лет опыта

        //TODO: Заполнить поле дата

        //TODO: Выбрать несколько Automation Tool

        //TODO: Выбрать континент из выпадающего списка
        //        WebElement continentSelector = driver.findElement(By.id("continents"));
        //        Actions builder = new Actions(driver);
        //        builder.click(continentSelector).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

        //        Select anotherContinentSelector = new Select(driver.findElement(By.id("continents")));
        //        anotherContinentSelector.selectByVisibleText("Australia");
        $("#continents").selectOptionContainingText("Australia");

        //
        //        //TODO: Выбрать несколько вариантов из списка Selenium Commands
        //        Select commandsSelector = new Select(driver.findElement(By.id("selenium_commands")));
        //        commandsSelector.selectByVisibleText("Browser Commands");
        //        commandsSelector.selectByVisibleText("Navigation Commands");
        $("#selenium_commands").selectOptionContainingText("Browser Commands");
        $("#selenium_commands").selectOptionContainingText("Wait Commands");

        System.out.println($("#selenium_commands").getSelectedOptions().texts());

        //TODO: Кликнуть на кнопку Button

        //TODO: Вывести в консоль текст лейбла Text1

        //TODO: Вывести в консоль текст лейбла Text2

        //TODO: Закрыть браузер
    }
}
