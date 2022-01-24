package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.TextFileUtility;
import utilities.WebSiteUtility;

public class LoginTest1WithTextFile
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	public SoftAssert sa;
	
	@Test(priority=1)
	public void logintest() throws Exception
	{
		sa=new SoftAssert();
		int size=TextFileUtility.getCountOfLinesInTextFile(
				         "src\\test\\resources\\datafiles\\logintestdata.txt");
		for(int i=1;i<=size;i++) 
		{
			String pieces[]=TextFileUtility.getValueInTextFile(
					"src\\test\\resources\\datafiles\\logintestdata.txt",i);
			String bn=pieces[0];
			String u=pieces[1];
			String uc=pieces[2];
			String p=pieces[3];
			String pc=pieces[4];
			//Common code
			//Create objects to Utility classes(have common methods)
			su=new WebSiteUtility();
			//Open browser
			driver=su.openBrowser(bn);
			wait=su.defineWait(driver);
			//Login testing
			try
			{
				//Define objects to page classes
				hp=new HomePage(driver,wait);
				lp=new LoginPage(driver,wait);
				cp=new ComposePage(driver,wait);
				lop=new LogoutPage(driver,wait);
				//Launch site by using URL in "config.properties" file
				su.launchSite(driver);
				//Enter UserID and click next
				hp.uidFill(u); 
				hp.uidNextClick();
				Thread.sleep(5000);
				//UserID testing
				if(uc.equalsIgnoreCase("blank"))  
				{
					if(hp.isBlankuidError())
					{
						Reporter.log("Uid blank test passed");
						sa.assertTrue(true);
					}
					else
					{
						Reporter.log("Uid blank test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
					}
				}
				else if(uc.equalsIgnoreCase("invalid")) 
				{
					if(hp.isInvaliduidError())
					{
						Reporter.log("Invalid UID test passed");
						sa.assertTrue(true);
					}
					else
					{
						Reporter.log("Invalid Uid test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
					}
				}
				else  //for valid UserID
				{
					if(hp.isPasswordDisplayed())
					{
						Reporter.log("Valid UID test passed");
						sa.assertTrue(true);
						//Password testing
						//Fill password and click next
						lp.pwdfill(p); 
						lp.pwdnextclick();
						Thread.sleep(5000);
						//Password Testing
						if(pc.equalsIgnoreCase("blank")) 
						{
							if(lp.isBlankpwdError())
							{
								Reporter.log("PWD blank test passed");
								sa.assertTrue(true);
							}
							else
							{
								Reporter.log("PWD blank test failed and see");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}
						}
						else if(pc.equalsIgnoreCase("invalid")) 
						{
							if(lp.isInvalidpwdError())
							{
								Reporter.log("Invalid PWD test passed");
								sa.assertTrue(true);
							}
							else
							{
								Reporter.log("Invalid PWD test failed and see:");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}	
						}
						else //valid Password
						{
							if(lp.isComposeDisplayed())
							{
								Reporter.log("Valid PWD test passed");
								sa.assertTrue(true);
								//Do logout
								lop.clickProfilePic();
								lop.clickSignOut();
							}
							else
							{
								Reporter.log("Valid PWD test failed and see:");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.assertTrue(false);
							}
						}
					}
					else
					{
						Reporter.log("Valid Uid test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.assertTrue(false);
					}
				}
				//close site
				su.closeSite(driver);
			}
			catch(Exception ex)
			{
				Reporter.log(ex.getMessage()+"and see:");
				String fp=su.captureScreenshot(driver);
				Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
				sa.assertTrue(false);
				su.closeSite(driver);
			}
		} //loop closing
		//Collate(collect and check) all assertions
		sa.assertAll();
	}
}
