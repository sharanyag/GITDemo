package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
	{

		WebDriver driver;

		public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css="div.cartSection h3")
		List<WebElement> cartprod;
		
		@FindBy(css=".totalRow button")
		WebElement checkout;
		
		public Boolean VerifyProductDisplay(String ProductName)
		{
		     Boolean b=cartprod.stream().anyMatch(p->p.getText().equalsIgnoreCase(ProductName));
return b;
		}
		
		public CheckoutPage GotoCheckoutPage()
		{
			checkout.click();
			//go to new page checkoutpage
			return new CheckoutPage(driver);
		}
}


