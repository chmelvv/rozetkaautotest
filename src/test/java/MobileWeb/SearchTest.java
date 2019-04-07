package MobileWeb;

import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Table;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Slf4j
public class SearchTest {

    RemoteWebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = getRemoteDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterClass
    public void close(){
        driver.close();
    }

    @Test
    public void searchForDefaultProduct(){
        Product defaultProduct = new Product();
        MainPage mainPage = new MainPage();

        mainPage
                .open()
                .search(defaultProduct.getName())
                .checkIsProductFound(defaultProduct.getId());
    }

    @DataProvider(name = "csv")
    public Object[][] csv(){
        // https://cwiki.apache.org/confluence/pages/viewpage.action?pageId=65875503
        DataContextPropertiesImpl properties = new DataContextPropertiesImpl();
            properties.put("type", "csv");
            properties.put("resource", "src/test/resources/ProductList.csv");
            properties.put("quote-char", '"');
            properties.put("separator-char", ',');
            properties.put("encoding", "UTF-8" );

        DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance().createDataContext(properties);

        Table table = dataContext.getDefaultSchema().getTable(0);
        DataSet dataSet = dataContext.query()
                .from( table )
                .selectAll()
                .execute();

        List<Row> rows = dataSet.toRows();

        Object[][] result = new Object[rows.size()][rows.get(0).size()];
        for(int i=0; i < rows.size(); i++){
            result[i] = rows.get(i).getValues();
        }

        return result;
    }

    @Test (dataProvider = "csv")
    public void searchProductList(String productName, String expProductId){
        MainPage mainPage = new MainPage();
        mainPage
                .open()
                .search(productName)
                .checkIsProductFound(expProductId);

    }

    private RemoteWebDriver getRemoteDriver()  {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.holdBrowserOpen = false;

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

        return driver;
    }


}
