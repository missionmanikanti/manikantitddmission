package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DummyTest2 
{
	public RemoteWebDriver driver;
	
	@Test(priority=1)
	public void method1()
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		System.out.println(sf.format(dt));
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.get("http://www.google.co.in");
	}
	
	@Test(priority=2)
	public void method2()
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		System.out.println(sf.format(dt));
		WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
		driver.get("http://www.google.co.in");
	}
}


















