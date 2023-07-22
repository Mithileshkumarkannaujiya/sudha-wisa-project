package appium.pageobjects;

import appium.generics.WaitStatement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SetupWizardScreen extends CommonElementScreen {

    final static String expTextForSearchingTransmitter = "Searching for the WiSA Audio System";
    final static String expTextForBluetoothConfirmation = "Make sure Bluetooth is on";
    final static String expTextForFindingSpeakersAndTransmitters = "Finding your wireless speakers and transmitter";
    final static String expTextForSelectDeviceDescription = "Please select the WiSA audio transmitter you'd like to use. To name your transmitter, click on the edit icon.";
    final static String expTextForSelectDeviceHeading = "Select A Device";

    public SetupWizardScreen(AndroidDriver<AndroidElement> anDriver) {
        super(anDriver);
        PageFactory.initElements(new AppiumFieldDecorator(anDriver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@id='WiSA']")
    private WebElement wisaLogo;
    @AndroidFindBy(id = "com.wisa.summit:id/audio_system")
    private WebElement searchingTransmitter;
    @AndroidFindBy(id = "com.wisa.summit:id/aa")
    private WebElement bluetoothConfirmation;
    @AndroidFindBy(id = "com.wisa.summit:id/tv_finding_speakers")
    private WebElement findingSpeakersAndTransmitters;
    @AndroidFindBy(id = "com.wisa.summit:id/cl_progress")
    private WebElement progressBar;
    @AndroidFindBy(id = "com.wisa.summit:id/iv_app_logo")
    private WebElement appLogo;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select A Device']")
    private WebElement selectDeviceText;
    @AndroidFindBy(id = "com.wisa.summit:id/tv_select_transmitter")
    private WebElement selectDeviceDescription;
    @AndroidFindBy(id = "com.wisa.summit:id/cl_device_name")
    private WebElement deviceName;
    @AndroidFindBy(id = "com.wisa.summit:id/nc_next")
    private WebElement NextBtn;
    @AndroidFindBy(id = "com.wisa.summit:id/iv_edit")
    private WebElement editBtn;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='WiSA Transmitter']")
    private WebElement TransmitterLogo;

    public void verifySplashScreen(){
        WaitStatement.explicitWaitForVisibilityOfElement(anDriver,10,wisaLogo);
        Assert.assertTrue(wisaLogo.isDisplayed());
        Assert.assertEquals(searchingTransmitter.getText(),expTextForSearchingTransmitter);
        Assert.assertEquals(bluetoothConfirmation.getText(),expTextForBluetoothConfirmation);
        Assert.assertTrue(progressBar.isDisplayed());
        Assert.assertEquals(findingSpeakersAndTransmitters.getText(),expTextForFindingSpeakersAndTransmitters);
    }
    public void verifySelectDeviceScreen(){
        WaitStatement.explicitWaitForVisibilityOfElement(anDriver,10,appLogo);
        Assert.assertTrue(appLogo.isDisplayed());
        Assert.assertEquals(selectDeviceText.getText(),expTextForSelectDeviceHeading);
        Assert.assertEquals(selectDeviceDescription.getText(),expTextForSelectDeviceDescription);
        Assert.assertEquals(deviceName.isDisplayed(),deviceName.isEnabled());
        Assert.assertEquals(NextBtn.isDisplayed(),NextBtn.isEnabled());
        Assert.assertEquals(editBtn.isDisplayed(),editBtn.isEnabled());
        Assert.assertTrue(TransmitterLogo.isDisplayed());
    }
}
