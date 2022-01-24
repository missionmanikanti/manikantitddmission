package practicetestng;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import utilities.WebSiteUtility;

public class TestngDummy26  
{
	public WebSiteUtility tu;
	public ATUTestRecorder recorder;
	public String vp;
	
	@BeforeSuite
	public void method1() throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		vp="target\\MySuite"+sf.format(dt); //".mov" is the default extension
		recorder=new ATUTestRecorder(vp,false); //false means no audio
		recorder.start();
	}
	
	@AfterSuite
	public void method4() throws ATUTestRecorderException
	{
		recorder.stop();
	}
}




