import Utilities.TestListener;
import common.DriverHandler;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import Utilities.TestListener;

@Listeners(TestListener.class)
public class WebsiteTest extends DriverHandler {

    HomePage homePage;

    WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        startBrowser();
        homePage = new HomePage();
    }

    @Test(priority = 1)
    public void RejectCookies() throws InterruptedException{
    homePage.clickReject();
    }
    @Test(priority = 2)
    public void CalculateSale() throws InterruptedException {
        homePage.Calculate();
    }
    @Test(priority = 3)
    public void DisplayResults() throws InterruptedException {
        homePage.displayResults();
    }

    //@AfterClass
    public void tearDown() {
        closeBrowser();
    }
}