package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductPage;

public class SubmitorderTest extends BaseTest {
	String ProductName = "IPHONE 13 PRO";
//first method -Array dataprovider
	/*
	@Test(dataProvider="getdata",groups={"smoke"}	)
public  void StandAlone(String Email,String pass,String productName)  throws InterruptedException, IOException {
*/
	//Second method Hashmap dataprovider
		@Test(dataProvider="getdata",groups={"smoke"}	)
		public  void StandAlone(HashMap<String,String> input)  throws InterruptedException, IOException {


//This line is need in all testcase so making it global and move to Basetest mark as beforetest anno.
//LandingPage lp=LaunchApplication();

			//ProductPage pp = lp.loginApplication("Email", "pass"); (First dataprovider mthd)
			ProductPage pp = lp.loginApplication(input.get("email"), input.get("pass")); //(second dataprovider mthd)


//ProductPage pp = new ProductPage(driver);
List<WebElement>products = pp.getProductlist();
pp.AddprodToCart(input.get("product"));
CartPage cp= pp.GotoCartPage();
Boolean b = cp.VerifyProductDisplay(input.get("product"));
Assert.assertTrue(true);

//validations dont go in page object file
CheckoutPage checkoutpage =cp.GotoCheckoutPage();
checkoutpage.Selectcountry("India");
ConfirmationPage confpage = checkoutpage.submitorder();

      String message= confpage.verifymessage();
        String Expectedmessage = "THANKYOU FOR THE ORDER.";
        Assert.assertTrue(message.equalsIgnoreCase(Expectedmessage));
	}
@Test//(dependsOnMethods = {"SubmitorderTest"})	// will first run submit order test and then excecute this test
public void OrderHistory()
{
	ProductPage pp = lp.loginApplication("redmond@gmail.com", "Iamking@000");
   OrderPage orderspage =    pp.GotoOrderPage();
   Assert.assertTrue(orderspage.VerifyOrderDisplay(ProductName));

	}
//first method -writting dataprovider within the code in array form
/*
@DataProvider
public Object[][] getdata()
{
	return new Object[][]  { {"redmond@gmail.com","Iamking@000","ZARA COAT 3"},{"rani@gmail.com","Iamking@000","IPHONE 13 PRO"} };
}
*/
//Second method - Using HashMap
/*
@DataProvider
public Object[][] getdata()
{
	HashMap<String,String>  map1= new HashMap<String,String>();
	map1.put("email","redmond@gmail.com");
	map1.put("pass","Iamking@000");
	map1.put("product","ZARA COAT 3");
	HashMap<String,String>  map2= new HashMap<String,String>();
	map2.put("email","redmond@gmail.com");
	map2.put("pass","Iamking@000");
	map2.put("product","IPHONE PRO 13");
	return new Object[][] {{map1},{map2}};
	
}*/

//Third method retriving data from json file

@DataProvider
public Object[][] getdata() throws IOException
{
	HashMap<String,String>  map1= new HashMap<String,String>();
	HashMap<String,String>  map2= new HashMap<String,String>();
	
	//call the json file method here
 List<HashMap<String,String>> data = getJsondataToMap("System.getProperty(user.dir)"+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
	//This is for manual hashmap     return new Object[][] {{map1},{map2}};
	return new Object[][] {{data.get(0),data.get(1)}};
	}
}


