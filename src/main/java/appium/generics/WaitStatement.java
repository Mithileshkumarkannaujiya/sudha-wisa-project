package appium.generics;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class WaitStatement {

        public static void staticWaitForSeconds(int sec)
        {
            try {
                Thread.sleep(sec*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void explicitWaitForVisibilityOfElement(AndroidDriver<AndroidElement> anDriver, int waitTime, WebElement ele)
        {
            new WebDriverWait(anDriver,waitTime).until(ExpectedConditions.visibilityOf(ele));
        }

        public static void implicitWaitForSec(AndroidDriver driver, int time){
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        }

        public static void explicitWaitForVisibilityOfAllElements(AndroidDriver<AndroidElement> anDriver, int waitTime, List<WebElement> ele)
        {
            new WebDriverWait(anDriver,waitTime).until(ExpectedConditions.visibilityOfAllElements(ele));
        }

        public static void explicitWaitForElementToBeClickable(AndroidDriver<AndroidElement> anDriver, int waitTime, WebElement ele)
        {
            new WebDriverWait(anDriver,waitTime).until(ExpectedConditions.elementToBeClickable(ele));
        }

        public static void explicitWaitForInvisibilityOfElement(AndroidDriver<AndroidElement> anDriver, int waitTime, WebElement ele)
        {
            try {
                new WebDriverWait(anDriver, waitTime).until(ExpectedConditions.invisibilityOf(ele));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        public static void fluentWait(AndroidDriver anDriver, WebElement ele)
        {
            new FluentWait<>(anDriver)
                    .withTimeout(Duration.ofSeconds(30))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .pollingEvery(Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(ele));

        }
        public static void fluentWaitForLisOfWebElements(AndroidDriver anDriver,List<WebElement> ele)
        {
            new FluentWait<>(anDriver)
                    .withTimeout(Duration.ofSeconds(20))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .pollingEvery(Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfAllElements(ele));
        }


    }


