package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.Testcomponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductPage;

public class ErrorValidations extends BaseTest {
	@Test(groups={"ErrorTest"},retryAnalyzer=Retry.class)
public  void StandAlone()  throws InterruptedException, IOException {

	
	
lp.loginApplication("redmond@gmail.com", "wrongpassword");
Assert.assertEquals("Incorrect email or password." , lp.GetErrormessage());
	}
	
	
	@Test (groups= {"smoke"},retryAnalyzer=Retry.class)
	public void producterrorvalidation()
	{
		
	String ProductName = "IPHONE 13 PRO";

	ProductPage pp = lp.loginApplication("redmond@gmail.com", "Iamking@000");
	List<WebElement>products = pp.getProductlist();
	pp.AddprodToCart(ProductName);
	CartPage cp= pp.GotoCartPage();
	Boolean b = cp.VerifyProductDisplay("IPHONE 8 PLUS");
	Assert.assertFalse(b);	

}}


