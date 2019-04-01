package SeleniumGrid;

import Pages.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Table;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Slf4j
public class SearchTest {

    RemoteWebDriver driver;
    private String browser;
    private String platform;

    @BeforeClass
    @Parameters ({"platform", "browser"})
    public void beforeClass(String platform, String browser){
        driver = getRemoteDriver(platform, browser);
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

    private RemoteWebDriver getRemoteDriver(String platform, String browser) {
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = false;

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
                    new URL("http://127.0.0.1:3333/wd/hub"),
                    dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }


}
