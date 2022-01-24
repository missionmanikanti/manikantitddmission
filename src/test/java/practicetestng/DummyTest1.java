package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DummyTest1 
{
	public RemoteWebDriver driver;
	
	@Test(priority=1)
	public void method1()
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		System.out.println(sf.format(dt));
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://www.google.co.in");
	}
	
	@Test(priority=2)
	public void method2()
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		System.out.println(sf.format(dt));
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.get("http://www.google.co.in");
	}
}








