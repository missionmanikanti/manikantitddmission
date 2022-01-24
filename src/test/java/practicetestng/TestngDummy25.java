package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import utilities.WebSiteUtility;

public class TestngDummy25 
{
	public WebSiteUtility tu;
	public ATUTestRecorder recorder;
	public String vp;
	
	@BeforeTest
	public void method1() throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		vp="target\\Mytest"+sf.format(dt); //".mov" is the default extension
		recorder=new ATUTestRecorder(vp,false); //false means no audio
		recorder.start();
	}
	
	@AfterTest
	public void method4() throws ATUTestRecorderException
	{
		recorder.stop();
	}
}




