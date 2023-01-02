package rahulshettyacademy.Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
//Retry: Due to some inconsistency at that moment in application sometestcases may fail. They r not real failures 
//they r called flaky failures so there is a technique in Testng Listeners to rerun the failed testcases one more time to make sure wether
// it is real failure or not.

//Tests which has IretryAnalyzer in its arguments only will retry so mention it in @Tests where ever we guess it ll fail.
public class Retry implements IRetryAnalyzer
{
//we need to rerun how manytimes is set in max try
	int count=0;
	int maxtry =1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(maxtry<1)
		{
			count++;
			return true;
		}
		return false;
	}

}
