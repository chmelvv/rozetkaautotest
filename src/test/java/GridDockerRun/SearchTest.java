package GridDockerRun;

import Data.Product;
import Pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.schema.Table;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class SearchTest extends BaseTestSeleniumGrid {

    private String browser;
    private String platform;

    @BeforeMethod
    @Parameters( {"platform", "browser" })
    public void setup(String platform, String browser){
        super.setupBrowser(platform, browser);
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

}
