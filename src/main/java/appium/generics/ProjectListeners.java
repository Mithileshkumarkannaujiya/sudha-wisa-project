package appium.generics;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class ProjectListeners implements ITestListener, WebDriverEventListener
{
    public static int iPassCount=0;
    public static int iFailCount=0;
    public static Logger imLog=Logger.getLogger(ProjectListeners.class);
    long startTime, stopTime;

    public ProjectListeners(){
        DateFormat dateFormat=new SimpleDateFormat("dd MMM, yyyy");
        Calendar cal = Calendar.getInstance();
        System.setProperty("timeStamp", dateFormat.format(cal.getTime()));
        PropertyUtill utill = new PropertyUtill();
        Properties prop = utill.file();
    }
    //IExecutionListener

    //ITestListener
    @Override
    public void onFinish(ITestContext arg0) {
        stopTime=System.currentTimeMillis();
        imLog.info("Test Suite execution finished: "+new Timestamp(new Date().getTime()));
        imLog.info("Total time for test suite execution: "+ (double)(stopTime-startTime)/1000+"seconds");

    }
    //ITestListener
    @Override
    public void onStart(ITestContext arg0) {
        startTime=System.currentTimeMillis();
        imLog.info("Test Suite Execution started: "+new Timestamp
                (new Date().getTime()));

    }
    //ITestListener
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
    //ITestListener
    @Override
    public void onTestFailure(ITestResult result) {
        iFailCount++;
        //System.out.println("Test Failure:"+ iFailCount);
        imLog.info("TestScript Name: '"+result.getName()+"' /Status--->Fail :(");

    }
    //ITestListener
    @Override
    public void onTestSkipped(ITestResult result) {
        imLog.info("Test has been skipped: "+result.getName()+result.getThrowable());
    }
    //ITestListener
    @Override
    public void onTestStart(ITestResult arg0) {
        imLog.info("Test execution has been started: "+new Timestamp
                (new Date().getTime()));
    }
    //ITestListener
    @Override
    public void onTestSuccess(ITestResult result) {
        iPassCount++;
        System.out.println("Test Pass: "+iPassCount);
        imLog.info("TestScript Name: '"+result.getName()+"' /Status--->Pass :)");
    }

    //WebDriverEventListener

    //WebDriverEventListener
    @Override
    public void afterClickOn(WebElement arg0, WebDriver arg1) {

    }
    //WebDriverEventListener
    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {

    }
    //WebDriverEventListener
    @Override
    public void afterNavigateBack(WebDriver arg0) {

    }
    //WebDriverEventListener
    @Override
    public void afterNavigateForward(WebDriver arg0) {

    }
    //WebDriverEventListener
    @Override
    public void afterNavigateRefresh(WebDriver arg0) {

    }
    //WebDriverEventListener
    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {

    }
    //WebDriverEventListener
    @Override
    public void afterScript(String arg0, WebDriver arg1) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }
    //WebDriverEventListener

    //WebDriverEventListener
    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {

    }
    //WebDriverEventListener
    @Override
    public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {

    }
    //WebDriverEventListener
    @Override
    public void beforeNavigateBack(WebDriver arg0) {

    }
    //WebDriverEventListener
    @Override
    public void beforeNavigateForward(WebDriver arg0) {

    }
    //WebDriverEventListener
    @Override
    public void beforeNavigateRefresh(WebDriver arg0) {

    }

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    //WebDriverEventListener
    @Override
    public void beforeNavigateTo(String arg0, WebDriver arg1) {

    }
    //WebDriverEventListener
    @Override
    public void beforeScript(String arg0, WebDriver arg1) {

    }
    //WebDriverEventListener
    @Override
    public void onException(Throwable t, WebDriver arg1) {
        imLog.fatal("Execution interrupted....");
        imLog.fatal(t.getMessage());

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub

    }
}



