package practicetestng;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TestngDummy5 
{
	public int x=10;
	@Test(successPercentage=50, invocationCount=20)
	public void method()
	{
	      if(x%2==0)
	      {
	    	  x++;
	      }
	      else
	      {
	    	  x++;
	    	  Assert.fail();
	      }
	}
}
