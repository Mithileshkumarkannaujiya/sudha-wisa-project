package appium.generics;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;

import static appium.generics.ProjectListeners.imLog;

public class ScreenshotLib
{
    static String fname;
    static String sshotPath;

    public static String getScreenshot(AndroidDriver<AndroidElement> anDriver, String filename)
    {
        EventFiringWebDriver efw = new EventFiringWebDriver(anDriver);
        imLog.info("Taking screenshot for: "+filename);
        File src=efw.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+File.separator+"Screenshots");
        File destFolder = new File(file,filename);
        if (destFolder.mkdirs())
        {
            System.out.println("Folder is created");
        }


        File[] listOfFiles=file.listFiles();
        if (listOfFiles.length !=0 && listOfFiles!=null)
        {
            for (File file1:listOfFiles) {
                try {
                    if (file1.equals(filename)) {
                        System.out.println(file1.getName());
                        FileUtils.deleteDirectory(file1);
                    }
                }
                catch (IOException e)
                {
                    imLog.error(e);
                }
            }
        }
        File[] newListOfFiles=destFolder.listFiles();
        for (int i =0; i<newListOfFiles.length;i++ )
        {
            newListOfFiles[i].delete();
        }
        try {
            FileUtils.copyFileToDirectory(src, destFolder,true);
        }
        catch (IOException e)
        {
            imLog.error(e);
        }
        for (File screenshotFile:destFolder.listFiles())
        {
            sshotPath=screenshotFile.getAbsolutePath();
        }
        return sshotPath;
    }
}
