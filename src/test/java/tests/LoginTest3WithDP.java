package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.WebSiteUtility;

public class LoginTest3WithDP
{
	public WebSiteUtility su;
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	
	@DataProvider(name="logintestdata", parallel=true)
	public Object[][] method1()
	{
		Object[][] x=new Object[][]{
				       {"chrome","magnitiait","valid","Magnitia@06","valid"},
				       {"chrome","","blank","N/A","N/A"},
				       {"chrome","magnitianonit","invalid","N/A","N/A"},
				       {"chrome","magnitiait","valid","","blank"},
				       {"chrome","magnitiait","valid","Magnitia@05","invalid"},
				};
		return(x);
	}
		
	@Test(priority=1, dataProvider="logintestdata")
	public void logintest(String bn,String u,String uc,String p,String pc) throws Exception
	{
		//common code
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
			//Launch site by using URL in properties file
			su.launchSite(driver);
			//Enter UserID and click next
			hp.uidFill(u); //parameterization
			hp.uidNextClick();
			Thread.sleep(5000);
			//UserID testing
			if(uc.equalsIgnoreCase("blank"))  
			{
				if(hp.isBlankuidError())
				{
					Reporter.log("Uid blank test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("Uid blank test failed and see:");
					String fp=su.captureScreenshot(driver);
					Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else if(uc.equalsIgnoreCase("invalid")) 
			{
				if(hp.isInvaliduidError())
				{
					Reporter.log("Invalid UID test passed");
					Assert.assertTrue(true);
				}
				else
				{
					Reporter.log("Invalid Uid test failed and see:");
					String fp=su.captureScreenshot(driver);
					Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
				}
			}
			else  //for valid UserID
			{
				if(hp.isPasswordDisplayed())
				{
					Reporter.log("Valid UID test passed");
					Assert.assertTrue(true);
					//Fill password and click next
					lp.pwdfill(p); //parameterization
					lp.pwdnextclick();
					Thread.sleep(5000);
					//Password Testing
					if(pc.equalsIgnoreCase("blank")) 
					{
						if(lp.isBlankpwdError())
						{
							Reporter.log("PWD blank test passed");
							Assert.assertTrue(true);
						}
						else
						{
							Reporter.log("PWD blank test failed and see");
							String fp=su.captureScreenshot(driver);
							Reporter.log(
			  "<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
							Assert.fail();
						}
					}
					else if(pc.equalsIgnoreCase("invalid")) 
					{
						if(lp.isInvalidpwdError())
						{
							Reporter.log("Invalid PWD test passed");
							Assert.assertTrue(true);
						}
						else
						{
							Reporter.log("Invalid PWD test failed and see:");
							String fp=su.captureScreenshot(driver);
							Reporter.log(
			    "<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
							Assert.fail();
						}	
					}
					else //valid Password
					{
						if(lp.isComposeDisplayed())
						{
							Reporter.log("Valid PWD test passed");
							Assert.assertTrue(true);
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
							Assert.fail();
						}
					}
				}
				else
				{
					Reporter.log("Valid Uid test failed and see:");
					String fp=su.captureScreenshot(driver);
					Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
					Assert.fail();
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
			su.closeSite(driver);
			Assert.fail();
		}
	}
}
