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
import utilities.ExcelFileUtility;
import utilities.WebSiteUtility;

public class LoginTest2WithExcelFile
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	public WebSiteUtility su;
	public HomePage hp;
	public LoginPage lp;
	public ComposePage cp;
	public LogoutPage lop;
	public ExcelFileUtility eu;
	public SoftAssert sa;
	
	@Test(priority=1)
	public void logintest() throws Exception
	{
		sa=new SoftAssert();
		eu=new ExcelFileUtility("src\\test\\resources\\datafiles\\logintestdata.xlsx");
		eu.openSheet("Sheet1");
		int nour=eu.getRowsCount();
		int nouc=eu.getColumnscount(0);
		eu.createResultColumn(nouc);
		//First row(index is 0) is names of columns, so start from 2nd row(index is 1)
		for(int i=1; i<nour;i++)
		{
			String bn=eu.getCellValue(i,0);
			String u=eu.getCellValue(i,1);
			String uc=eu.getCellValue(i,2);
			String p=eu.getCellValue(i,3);
			String pc=eu.getCellValue(i,4);
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
						eu.setCellValue(i,nouc,"Uid blank test passed");
						Reporter.log("Uid blank test passed");
						sa.assertTrue(true);
					}
					else
					{
						eu.setCellValue(i,nouc,"Uid blank test failed");
						Reporter.log("Uid blank test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				else if(uc.equalsIgnoreCase("invalid")) 
				{
					if(hp.isInvaliduidError())
					{
						eu.setCellValue(i,nouc,"Invalid UID test passed");
						Reporter.log("Invalid UID test passed");
						sa.assertTrue(true);
					}
					else
					{
						eu.setCellValue(i,nouc,"Invalid Uid test failed");
						Reporter.log("Invalid Uid test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				else  //for valid UserID
				{
					if(hp.isPasswordDisplayed())
					{
						eu.setCellValue(i,nouc,"Valid UID test passed");
						Reporter.log("Valid UID test passed");
						sa.assertTrue(true);
						//Password testing
						//Fill password and click next
						lp.pwdfill(p); //parameterization
						lp.pwdnextclick();
						Thread.sleep(5000);
						//Password Testing
						if(pc.equalsIgnoreCase("blank")) 
						{
							if(lp.isBlankpwdError())
							{
								eu.setCellValue(i,nouc,"PWD blank test passed");
								Reporter.log("PWD blank test passed");
								sa.assertTrue(true);
							}
							else
							{
								eu.setCellValue(i,nouc,"PWD blank test failed");
								Reporter.log("PWD blank test failed and see");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
			  "<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}
						}
						else if(pc.equalsIgnoreCase("invalid")) 
						{
							if(lp.isInvalidpwdError())
							{
								eu.setCellValue(i,nouc,"Invalid PWD test passed");
								Reporter.log("Invalid PWD test passed");
								sa.assertTrue(true);
							}
							else
							{
								eu.setCellValue(i,nouc,"Invalid PWD test failed");
								Reporter.log("Invalid PWD test failed and see:");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
			"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}	
						}
						else //valid Password
						{
							if(lp.isComposeDisplayed())
							{
								eu.setCellValue(i,nouc,"Valid PWD test passed");
								Reporter.log("Valid PWD test passed");
								sa.assertTrue(true);
								//Do logout
								lop.clickProfilePic();
								lop.clickSignOut();
							}
							else
							{
								eu.setCellValue(i,nouc,"Valid PWD test failed");
								Reporter.log("Valid PWD test failed and see:");
								String fp=su.captureScreenshot(driver);
								Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
								sa.fail();
							}
						}
						
					}
					else
					{
						eu.setCellValue(i,nouc,"Valid Uid test failed");
						Reporter.log("Valid Uid test failed and see:");
						String fp=su.captureScreenshot(driver);
						Reporter.log(
				"<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
						sa.fail();
					}
				}
				//close site
				su.closeSite(driver);
			}
			catch(Exception ex)
			{
				eu.setCellValue(i, nouc,ex.getMessage());
				Reporter.log(ex.getMessage()+"and see:");
				String fp=su.captureScreenshot(driver);
				Reporter.log(
		       "<a href=\""+fp+"\"><img src=\""+fp+"\" height=\"100\" width=\"100\"/></a>");
				su.closeSite(driver);
				sa.fail(); //equal to sa.assertTrue(false);
			}
		} //loop closing
		//Save and close excel
		eu.saveAndCloseExcel();
		//Collate(collect and check) all assertions
		sa.assertAll();
	}
}
