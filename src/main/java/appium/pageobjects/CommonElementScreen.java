package appium.pageobjects;

import appium.generics.WaitStatement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CommonElementScreen {

    protected static AndroidDriver<AndroidElement> anDriver;

    public CommonElementScreen(AndroidDriver<AndroidElement> anDriver) {
        this.anDriver = anDriver;
        PageFactory.initElements(new AppiumFieldDecorator(anDriver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
    private WebElement bluetoothAllowBtn;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Deny']")
    private WebElement bluetoothDenyBtn;

    //btnName should = WHILE USING THE APP, ONLY THIS TIME, DON'T ALLOW, ALLOW,
    public WebElement getLocationAccessBtns(AndroidDriver driver, String btnName) {
        return driver.findElementByXPath("//android.widget.Button[contains(@text,'" + btnName.toUpperCase() + "')]");
    }

    public void allowLocationAndBluetoothPermissions() {
        WaitStatement.explicitWaitForElementToBeClickable(anDriver, 10, getLocationAccessBtns(anDriver,"WHILE USING THE APP"));
        Assert.assertEquals(getLocationAccessBtns(anDriver,"WHILE USING THE APP").isDisplayed(),getLocationAccessBtns(anDriver,"WHILE USING THE APP").isEnabled());
        getLocationAccessBtns(anDriver, "WHILE USING THE APP").click();
        getLocationAccessBtns(anDriver, "allow").click();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            Alert alert=anDriver.switchTo().alert();
            alert.accept();
        }
        catch (NoAlertPresentException e){
            System.out.println("Alert not present.");
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            anDriver.findElement(By.xpath("//android.widget.Button[@text='Allow']")).isDisplayed();
            anDriver.findElement(By.xpath("//android.widget.Button[@text='Allow']")).click();
        }
        catch (NoSuchElementException e){
            System.out.println("Element not present.");
            e.printStackTrace();
        }
    }


}
