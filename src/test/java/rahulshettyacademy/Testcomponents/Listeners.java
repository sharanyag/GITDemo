package rahulshettyacademy.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.Resources.ExtentReporterNG;
//In this code , when test starts excecution test entry is created
//When test pass a log is created nothinh much
//when test fails , failling it dynamically and  log created and screenshot is taken and attached .
//listeners code must be regoganised by testng so add it in testng file 
public class Listeners extends BaseTest  implements ITestListener {
	ExtentTest test ;	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	//making it threadsafe ,so even if we run concurrently each test object has its own thread.
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	//Now push out test object into thread to make it threadsafe
	
	@Override
	public void onTestStart(ITestResult result)
	{
//after starting testng test it comes to listener file and checks the result and gets methodname  ie testcasename 
		test = extent.createTest(result.getMethod().getMethodName());	
		extentTest.set(test);//Now when ever any test runs inside this thread it assigns a unique thread id
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		//test.log(Status.FAIL, "Test Failed");
		//or 
		//test.fail(result.getThrowable()); To make threadsafe use below code
		extentTest.get().fail(result.getThrowable());
		//screenshot
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());//giving life to driver
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filepath = null;
		try {
			filepath = getScreenShot( result.getMethod().getMethodName(),driver); 
			//base test had driver initialized and sent that driver to each testcase, we r passing same driver to screenshotcapture using above code
			//OnTestcase failure method catches the testcase details in results obj and also it has driver info in results obj using above driver code
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //passing testcasename as arguments to method
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName() );
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	
	
}
