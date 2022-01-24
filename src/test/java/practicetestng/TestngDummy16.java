package practicetestng;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class TestngDummy16
{
	@Test(priority=1)
	@Parameters({"country","firstname","lastname"})
	public void displaycountry(String c, String fn, String ln)
	{
		System.out.println(fn+" "+ln+" belongs to "+c);
	}
	
	@Test(priority=2)
	@Parameters({"firstname","lastname"})
	public void displayfullname(String fn, String ln)
	{
		System.out.println(fn+" "+ln);
	}
	
	@Test(priority=3)
	public void display()
	{
		System.out.println("done");
	}
}
