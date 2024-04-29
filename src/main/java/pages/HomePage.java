package pages;

import Utilities.TestListener;
import common.DriverHandler;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.omg.PortableServer.ServantActivatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.window;

@Listeners(TestListener.class)
public class HomePage extends DriverHandler {
    //Page Objects
    @FindBy(xpath = "/html/body/epaas-consent-drawer-shell//html/body/div/div/section/div[3]/div/div[2]/button[1]")
    public WebElement RejectCookies;
    @FindBy(xpath = "/html/body/epaas-consent-drawer-shell//html/body/div/div/section/div[3]/div/div[2]/button[2]")
    public WebElement ContinueButton;
    @FindBy(xpath = "//*[@id=\"container-c041021c62\"]/div/div/root-shell/div/nav[1]/div[2]/ul/li[1]/a/span")
    WebElement modelLink;
    @FindBy(xpath = "/html/body/div[3]/main/div[1]/div[10]/div/div[1]/div[2]/div/div/div/div[2]/a")
    WebElement CalculatorTab;
    @FindBy(xpath = "/html/body/div[3]/main/div[1]/div[10]/div/div[1]/div[3]/div/div/div[3]/div/div[2]/div[2]/div/ul/li/a/span")
    WebElement CalculatorButton;

    @FindBy(id = "purchasePrice")
    WebElement purchasePriceTextbox;
    @FindBy(xpath = "//*[@id=\"depositValue\"]")
    WebElement depositTextbox;
    @FindBy(id = "interestRate")
    WebElement interestRatedropdown;
    @FindBy(xpath = "//*[@id=\"energy\"]/div/div[2]/div[1]/div[2]")
    WebElement paymentPeriodslider;
    @FindBy(xpath = "/html/body/div/div/div/section/div[2]/form/fieldset/div[1]/div/div[2]/div[3]/div/div[2]/div[1]/div[4]")
    WebElement Balloonslider;

    @FindBy(css = "li.slider-item:nth-child(2) > a:nth-child(1)")
    WebElement supportAndservices;
    @FindBy(id = "balloonValue")
    WebElement balloonValue;
    @FindBy(id = "monthlyRepayment")
    WebElement monthlyRepayment;

    WebDriverWait wait;

    //Initialize Page Objects
    public HomePage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void clickReject() {
        WebElement shadowHost = driver.findElement(By.tagName("epaas-consent-drawer-shell"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement Reject = shadowRoot.findElement(By.className("button-text"));
        Reject.click();
        WebElement Continue = shadowRoot.findElement(By.className("accept-button"));
        Continue.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public void Calculate() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", supportAndservices);
        wait.until(ExpectedConditions.elementToBeClickable(supportAndservices)).click();
        wait.until(ExpectedConditions.elementToBeClickable(CalculatorTab)).click();
        wait.until(ExpectedConditions.visibilityOf(CalculatorButton)).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        js.executeScript("javascript:window.scrollBy(250,750)");
        System.out.println("Balloon slider location "+Balloonslider.getLocation());//Get the x and y axis
        System.out.println("Balloon slider location "+Balloonslider.getSize());
        System.out.println("repayment slider location "+paymentPeriodslider.getLocation());//Get the x and y Axis
        System.out.println("repayment slider location "+paymentPeriodslider.getSize());
        purchasePriceTextbox.sendKeys(getProp().getProperty("purchaseprice"));
        depositTextbox.sendKeys(getProp().getProperty("deposit"));
        Select dropdown = new Select(interestRatedropdown);
        dropdown.selectByVisibleText(getProp().getProperty("InterestRate"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(paymentPeriodslider, 0, 60).perform();//firefox had Javascript errors so could not get the coordinates and chrome froze
        actions.dragAndDropBy(Balloonslider, 0, 5).perform();//firefox had Javascript errors so could not get the coordinates and chrome froze
    }
    public void displayResults()throws InterruptedException {
        System.out.println("The balloon payment amount is :" + balloonValue.getText() + " and the monthly repayment amount is :" + monthlyRepayment);

    }
    }
