package practicetestng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngDummy11
{
	@DataProvider(name="textfiledata")
	public Object[][] method1() throws Exception
	{
		Object[][] data=new Object[15][5];
		File f=new File("src\\test\\resources\\datafiles\\logintestdata.txt");
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		String line;
		int i=0;
		while((line=br.readLine())!=null)
		{
			String[] pieces=line.split(",");
			int j=0;
			for(String piece:pieces)
			{
				data[i][j]=piece;
				j++; //next column
			}
			i++;//next row
		}
		br.close();
		fr.close();
		return(data);
	}
	
	@Test(dataProvider="textfiledata")
	public void method2(String x, String y, String z, String p, String q)
	{
		String w=x+"-"+y+"-"+z+"-"+p+"-"+q;
		System.out.println(w);
	}
}
