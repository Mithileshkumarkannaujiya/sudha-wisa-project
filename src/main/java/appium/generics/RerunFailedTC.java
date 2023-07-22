package appium.generics;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Properties;

import static appium.generics.ProjectListeners.imLog;

public class RerunFailedTC implements IRetryAnalyzer
{
    PropertyUtill util =new PropertyUtill();
    Properties prop=util.file();


    private int retryCount=Integer.valueOf(prop.getProperty("min_retry_count"));
    private int maxRetryCount=Integer.valueOf(prop.getProperty("max_retry_count"));

    @Override
    public boolean retry(ITestResult result) {

        while (retryCount<maxRetryCount)
        {
            imLog.info("Retrying method: "+result.getMethod().getMethodName()+" with status "+getResultStatusName(result.getStatus())+" for the "+(retryCount+1)+ " time(s)");
            retryCount++;
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status)
    {
        String resultName=null;
       if (status==1){
           resultName="SUCCESS";
       }
       else if (status==2)
       {
           resultName="FAILURE";
       }
       else if (status==3)
       {
           resultName="SKIPPED";
       }
        return resultName;

    }

}
