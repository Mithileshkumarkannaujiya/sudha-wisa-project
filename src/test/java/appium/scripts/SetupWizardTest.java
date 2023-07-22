package appium.scripts;

import appium.generics.BaseAndroidClass;
import appium.pageobjects.SetupWizardScreen;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SetupWizardTest extends BaseAndroidClass {

    SetupWizardScreen sws;

    @Test
    public void TC01_verifySelectDeviceScreen(){
            extentTest=extent.createTest("TC01: Verify select device screen");
            extentTest.assignAuthor("Sudha");
            extentTest.assignCategory("Regression suite");
            sws=new SetupWizardScreen(getDriver());
            sws.allowLocationAndBluetoothPermissions();
            sws.verifySelectDeviceScreen();
            extentTest.log(Status.INFO,"Select device screen is verified successfully");
    }

}
