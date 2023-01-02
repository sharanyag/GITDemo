package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.Testcomponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductPage;

public class StepDefinition extends BaseTest {
	
	public LandingPage lp;
	public ProductPage pp;
	public ConfirmationPage confpage;
	
	@Given("I landed on ecommerece page")
	public void I_landed_on_Ecommerece_Page() throws IOException
	{
		lp = LaunchApplication(); //code
	}

@Given("^Loggedin with username (.+) and password (.+) $")//^$ is to inform us this line has parameter declaration
public void Loggedin_with_username_and_password(String username,String password)

{
		 pp= lp.loginApplication(username,password);
}
	
	 @When("^I addthe product (.+) to cart$")
	 public void  I_addthe_product_to_cart(String productname)
	 {
		 List<WebElement>products = pp.getProductlist();
		 pp.AddprodToCart(productname);
	 }
	 @When("^checkout (.+) and submit the order$")
	 public void  Checkout_and_submit_the_order(String productname)
	 {
		 CartPage cp= pp.GotoCartPage();
		 Boolean b = cp.VerifyProductDisplay(productname);
		 Assert.assertTrue(true);
		 CheckoutPage checkoutpage =cp.GotoCheckoutPage();
		 checkoutpage.Selectcountry("India");
		 ConfirmationPage confpage = checkoutpage.submitorder();
	 }
	 @Then( "{string} message is displayed on confirmation page")
	 public void message_displayed(String string)
	 {
		 String message = confpage.verifymessage();
		 Assert.assertTrue(message.equalsIgnoreCase(string));

	 }
	 
	}
