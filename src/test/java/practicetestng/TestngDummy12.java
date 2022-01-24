package practicetestng;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngDummy12
{
	@DataProvider(name="exceldata")
	public Object[][] method1() throws Exception
	{
		File f=new File("src\\test\\resources\\datafiles\\logintestdata.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi); //get my workbook 
		Sheet sh=wb.getSheetAt(0);  //get 1st sheet from workbook
		int RowNum=sh.getPhysicalNumberOfRows();   //count number of Rows
		Row r=sh.getRow(1); //2nd row 
		int ColNum=r.getLastCellNum(); // get last ColNum 
		Object data[][]=new Object[RowNum-1][ColNum]; //array size as per count of data
		for(int i=1; i<RowNum; i++) //Loop works from 2nd row in excel
		{  
	        for (int j=0; j<ColNum; j++) //Loop works o columns
	        {
	            try
	            {
	            	//formatter get all values as string which are in int, float, date,…etc
	            	DataFormatter formatter=new DataFormatter();
	            	data[i-1][j]=formatter.formatCellValue(sh.getRow(i).getCell(j));
	            }
	            catch(Exception ex)
	            {
	            	data[i-1][j]="";
	            }
	        }
		}
		fi.close();
		wb.close();
		return(data);
	}
	
	@Test(dataProvider="exceldata")
	public void method2(String x, String y, String z, String p, String q)
	{
		String w=x+"-"+y+"-"+z+"-"+p+"-"+q;
		System.out.println(w);
	}
}
