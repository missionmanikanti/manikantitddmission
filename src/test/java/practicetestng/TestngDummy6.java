package practicetestng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestngDummy6
{
	@Test(priority=1)
	public void openbrowser()
	{
		System.out.println("launch browser");
	}
	
	@Test(priority=2, dependsOnMethods={"openbrowser"})
	public void login() 
	{
		System.out.println("do login");
		Assert.fail();
	}
	
	@Test(priority=3)
	public void sendMail()
	{
		System.out.println("send a mail");
	}
}






