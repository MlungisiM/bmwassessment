package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverHandler {

    public static WebDriver driver;
    static Properties prop = null;

    public ExtentReports report;
    public ExtentTest logger;

    public  static String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        return dateFormat.format(dateNow).toString();
    }

    //Load config.properties file
    public DriverHandler() {

        prop = new Properties();

        try {
            FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/resources/config.properties");
            prop.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProp() {
        return prop;
    }

    //Initialize driver and start browser
    public void startBrowser() {
        String browserName = prop.getProperty("browser");

        if ("chrome".equalsIgnoreCase(browserName)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browserName)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if("edge".equalsIgnoreCase(browserName)){
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
        } else
            System.out.print("unable to start " + browserName + " browser");

        long start = System.currentTimeMillis();
        driver.get(prop.getProperty("url"));
        long finish = System.currentTimeMillis();
        long Total = (finish-start)/1000;
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Total time it took the system to respond is: "+Total+" seconds");

    }

    public void takeScreenshot(String screenshotName)
    {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file, new File("./reports/" + screenshotName + ".png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Tear down method - return driver into its initial state
    public void closeBrowser() {
        report.flush();
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
