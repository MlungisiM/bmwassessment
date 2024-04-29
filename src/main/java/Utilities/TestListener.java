package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import common.DriverHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;

public class TestListener extends DriverHandler implements ITestListener{
    private static ExtentReports extent = new ExtentReports();
    private static ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/BMW_Report_"+generateDateTimeString()+".html");
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ITestListener.super.onTestSuccess(iTestResult);
        String passedTest = iTestResult.getName();
        String screenshotsDir = "./reports/";
        reporter.config().setDocumentTitle("BMW Test Results");
        reporter.config().setTheme(Theme.DARK);

        takeScreenshot(passedTest);

        extent.attachReporter(reporter);
        extent.createTest(passedTest).log(Status.PASS,"Test Passed")
                .info(MediaEntityBuilder.createScreenCaptureFromPath(passedTest+".png").build());
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ITestListener.super.onTestFailure(iTestResult);
        String failedTest = iTestResult.getName();
        String screenshotsDir = "./reports/";
        reporter.config().setDocumentTitle("BMW Test Results");
        reporter.config().setTheme(Theme.DARK);

        takeScreenshot(failedTest);

        extent.attachReporter(reporter);
        extent.createTest(failedTest).log(Status.FAIL,"Test Failed").addScreenCaptureFromPath(failedTest+".png");
        extent.flush();

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file, new File(failedTest+".png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

}