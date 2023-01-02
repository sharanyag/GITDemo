package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		// Extent Reports and ExtentSparkreporter helps to generate reports
		// default syntax where reports package is automatically created and reports
		// generated in index.html name
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html"; 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sharanya");
		extent.createTest(path); //This step v cant write for each TC,so we use listeners inside testcomponents)
	return extent;
	}

}
