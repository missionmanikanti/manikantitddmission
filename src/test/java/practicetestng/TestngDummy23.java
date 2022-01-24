package practicetestng;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestngDummy23
{
	public RemoteWebDriver driver;
	public ATUTestRecorder recorder;
	public String vp;
	
	@BeforeMethod
	public void method1(Method m) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		vp="target\\"+m.getName()+"-"+sf.format(dt); //".mov" is the default extension
		recorder=new ATUTestRecorder(vp,false); //false means no audio
		recorder.start();
	}
	
	@Test(priority=1)
	public void magnitia() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://www.magnitia.com");
		Thread.sleep(5000);
		driver.close();
	}
	
	@Test(priority=2)
	public void gmail() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://www.gmail.com");
		Thread.sleep(5000);
		driver.close();
	}
	
	@AfterMethod
	public void method4() throws ATUTestRecorderException
	{
		recorder.stop();
	}
}




