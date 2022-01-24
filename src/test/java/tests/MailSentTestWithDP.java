package tests;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.WebSiteUtility;

public class MailSentTestWithDP
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	public ATUTestRecorder recorder;
	public String vp;
	
	@BeforeClass
	public void setup() throws Exception
	{
		//Create objects to Utility classes(have common methods)
		su=new WebSiteUtility();
		//start video recording
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		vp="target\\mailsenttest-"+sf.format(dt); //".mov" is the default extension
		recorder=new ATUTestRecorder(vp,false); //false means no audio
		recorder.start();
	}
	
	@DataProvider(parallel=true)
	public Object[][] providerMethod(Method m)
	{
		Object[][] x=new Object[][]
		{
			{"chrome","magnitiait","Magnitia@06","magnitiait@gmail.com","hi","Dont get sleeping in CR","C:\\Users\\Public\\Pictures\\Sample Pictures\\koala.jpg"},
			{"firefox","magnitiait","Magnitia@06","magnitiait@gmail.com","hi","Don’t see girls in CR","C:\\Users\\Public\\Pictures\\Sample Pictures\\lighthouse.jpg"},
			{"opera","magnitiait","Magnitia@06","magnitiait@gmail.com","hello","Don’t disturb them","C:\\Users\\Public\\Pictures\\Sample Pictures\\tulips.jpg"},
			{"edge","magnitiait","Magnitia@06","magnitiait@gmail.com","hello","Don’t disturb them","C:\\Users\\Public\\Pictures\\Sample Pictures\\tulips.jpg"}
		};
		return(x);
	}
	
	@Test(priority=1,dataProvider="providerMethod")
	public void mailSentTest(String bn, String u, String p, String t, String s, 
			                                      String b, String fp) throws Exception
	{
		//Mail compose testing with multiple test data in cross browser environment
		//Open browser by creating driver object
		driver=su.openBrowser(bn);
		//define wait object by using driver object
		wait=su.defineWait(driver);
		//Create objects to page classes by using driver and wait objects
		HomePage hp=new HomePage(driver,wait);
		LoginPage lp=new LoginPage(driver,wait);
		ComposePage cp=new ComposePage(driver,wait);
		LogoutPage lop=new LogoutPage(driver,wait);
		//Launch site by using url in properties file
		su.launchSite(driver);
		//Do login using valid data
		hp.uidFill(u); //parameterization
		hp.uidNextClick();
		Thread.sleep(5000);
		lp.pwdfill(p); //parameterization
		lp.pwdnextclick();
		Thread.sleep(5000);
		//Goto compose operation
		try
		{
			cp.clickCompose();
			cp.fillTo(t);
			cp.fillSubject(s);
			cp.fillBody(b);
			cp.attachment(fp);
			cp.clickSend();
			Thread.sleep(5000);
			//compose testing
			String msg=cp.getOutputMessage();
			if(!msg.contains("Sorry")) //not contains Sorry
			{
				Reporter.log("Compose test passed and we got "+msg);
				Assert.assertTrue(true);
			}
			else
			{
				Reporter.log("Compose test failed and see:");
				String sp=su.captureScreenshot(driver);
				Reporter.log(
			  "<a href=\""+sp+"\"><img src=\""+sp+"\" height=\"100\" width=\"100\"/></a>");
				Assert.fail();
			}
			//Do logout
			lop.clickProfilePic();
			lop.clickSignOut();
			//close site
			su.closeSite(driver);
		}
		catch(Exception ex)
		{
			Reporter.log(ex.getMessage()+" and see:");
			String sp=su.captureScreenshot(driver);
			Reporter.log(
		       "<a href=\""+sp+"\"><img src=\""+sp+"\" height=\"100\" width=\"100\"/></a>");
			//close site
			su.closeSite(driver);
			Assert.fail();
		}
	}
	
	@AfterClass
	public void method4() throws ATUTestRecorderException
	{
		recorder.stop();
	}
}

