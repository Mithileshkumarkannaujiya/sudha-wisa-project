package appium.generics;


import java.io.*;
import java.util.Properties;

public class PropertyUtill
{

    InputStream is=null;
    Properties prop = new Properties();

    public Properties file()
    {
        try {
            File f=new File("src/main/resources");
            is = new FileInputStream(new File(f,"appium.properties"));
            prop.load(is);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return prop;
    }
}
