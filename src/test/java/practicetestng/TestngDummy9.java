package practicetestng;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngDummy9
{
	@DataProvider(name="mydata")
	public Object[][] datamethod(Method m)
	{
		if(m.getName().equalsIgnoreCase("testmethod1"))
		{
			Object[][] x=new Object[][]{
				                          {12,34,45},
				                          {21,43,65},
				                          {24,23,-65}
					                    };
			return(x);
		}
		else if(m.getName().equalsIgnoreCase("testmethod2"))
		{
			Object[][] x=new Object[][]{
											{"abdul","kalam","11"},
											{"Dhoni","MS","34.8"},
											{"kohli","anushka","1437785"},
											{"Rahul","Gandhi",""}
					         };
			return(x); 
		}
		else
		{
			Object[][] x=new Object[][]{
											{"abdul","kalam","11"},
											{"Dhoni","MS","34.8"}
								};
			return(x); 
		}
	}
	
	@Test(priority=1,dataProvider="mydata")
	public void testmethod1(int x, int y, int z)
	{
		int w=x+y+z;
		System.out.println(w);
	}
	
	@Test(priority=2,dataProvider="mydata")
	public void testmethod2(String x, String y, String z)
	{
		String w=x+"-"+y+"-"+z;
		System.out.println(w);
	}
}




