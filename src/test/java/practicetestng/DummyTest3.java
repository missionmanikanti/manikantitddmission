package practicetestng;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DummyTest3 
{
	@Test(priority=1)
	public void method1(ITestContext ct)
	{
		int a=10;
		int b=30;
		int z=a+b;
		ISuite s=ct.getSuite();
		//Setting an attribute with name and its value at suite level
		s.setAttribute("output",z);
	}
}








