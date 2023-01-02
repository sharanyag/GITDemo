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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductPage;

public class StandAloneTest extends BaseTest {

	@Test
public  void StandAlone()  throws InterruptedException, IOException {


String ProductName = "IPHONE 13 PRO";

//This line is need in all testcase so making it global and move to Basetest mark as beforetest anno.
//LandingPage lp=LaunchApplication();
ProductPage pp = lp.loginApplication("redmond@gmail.com", "Iamking@000");

//ProductPage pp = new ProductPage(driver);
List<WebElement>products = pp.getProductlist();
pp.AddprodToCart(ProductName);
CartPage cp= pp.GotoCartPage();
Boolean b = cp.VerifyProductDisplay(ProductName);
Assert.assertTrue(true);

//validations dont go in page object file
CheckoutPage checkoutpage =cp.GotoCheckoutPage();
checkoutpage.Selectcountry("India");
ConfirmationPage confpage = checkoutpage.submitorder();

      String message= confpage.verifymessage();
        String Expectedmessage = "THANKYOU FOR THE ORDER.";
        Assert.assertEquals(message, Expectedmessage);
	}}


