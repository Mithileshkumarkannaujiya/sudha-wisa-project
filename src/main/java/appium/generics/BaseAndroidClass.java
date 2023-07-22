package appium.generics;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static appium.generics.ProjectListeners.imLog;


public class BaseAndroidClass {

    String appiumServiceUrl="http://127.0.0.1:4723/wd/hub";
    protected static AndroidDriver<AndroidElement> anDriver;
    PropertyUtill utill = new PropertyUtill();
    public Properties prop = utill.file();
    public ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent ;
    protected static ExtentTest extentTest;
    private  AppiumDriverLocalService service;

    @BeforeTest
    public void LoadReport() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "ExtentReportHtml" + File.separator +getClass().getSimpleName()+" "
                    +(new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date()) + ".html"));
        htmlReporter.config().setCSS(".r-img { width: 30%; }");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "Beta apk");
        extent.setSystemInfo("Host Name", "Wisa Appium");
        extent.setSystemInfo("User Name", "Sudha Yadav");
        htmlReporter.config().setDocumentTitle("Wisa Appium Report");
        htmlReporter.config().setReportName("Wisa Appium Report");
        htmlReporter.config().setTheme(Theme.DARK);
        //initEmulator();
    }

    @BeforeMethod
    @Parameters({"AUTOMATION_NAME", "DEVICE_NAME", "PLATFORM_NAME", "PLATFORM_VERSION", "appPackage", "appActivity", "app"})
    public void setUp(Method method, String AUTOMATION_NAME, String DEVICE_NAME, String PLATFORM_NAME, String PLATFORM_VERSION, String appPackage, String appActivity, String app) throws MalformedURLException {
        extentTest=extent.createTest(method.getName());
        DesiredCapabilities dc = new DesiredCapabilities();
     /*   dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("AUTOMATION_NAME"));
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("DEVICE_NAME"));
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("PLATFORM_NAME"));
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("PLATFORM_VERSION"));
        dc.setCapability("appPackage", prop.getProperty("appPackage"));
        dc.setCapability("appActivity", prop.getProperty("appActivity"));
        dc.setCapability(MobileCapabilityType.APP, prop.getProperty("app"));*/

        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        dc.setCapability("appPackage", appPackage);
        dc.setCapability("appActivity", appActivity);
        dc.setCapability(MobileCapabilityType.APP, app);

        URL url = new URL(appiumServiceUrl);
        anDriver = new AndroidDriver<>(url, dc);
        WaitStatement.implicitWaitForSec(anDriver,20);
    }

    public AndroidDriver getDriver()
    {
        return anDriver;
    }

    @AfterMethod
    public void postMethod(ITestResult result) {

        String filename = result.getMethod().getMethodName();
        System.out.println(result.getStatus());
        try {
            switch (result.getStatus()) {
                case 1:
                    extentTest.log(Status.PASS, "Test: Passed");
                    break;

                case 3:
                    extentTest.log(Status.SKIP, MarkupHelper.createLabel("Test: Skipped", ExtentColor.ORANGE));
                    break;

                case 2:
                    extentTest.log(Status.FAIL, MarkupHelper.createLabel("Failed: " + result.getName(), ExtentColor.RED));
                    extentTest.log(Status.FAIL, MarkupHelper.createLabel("" + result.getThrowable(), ExtentColor.TRANSPARENT));
                    ArrayList stepslogs = new ArrayList<String>();
                    for (int i = 0; i < result.getThrowable().getStackTrace().length; i++) {
                        stepslogs.add(result.getThrowable().getStackTrace()[i]);

                        extentTest.log(Status.FAIL, MarkupHelper.createLabel("" + stepslogs.get(i), ExtentColor.TRANSPARENT));
                    }
                    String screenshotPath = ScreenshotLib.getScreenshot(anDriver, filename);
                    extentTest.addScreenCaptureFromPath(screenshotPath, filename);
                    imLog.info("Screenshot is taken for method " + filename);
            }
        } catch (Exception e) {
            imLog.info(e);
        }
        if (anDriver != null) {
            anDriver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }

}



