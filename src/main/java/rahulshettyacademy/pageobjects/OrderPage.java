package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
	{

		WebDriver driver;

		public OrderPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css="tr  td:nth-child(3)")
		List<WebElement> orderprod;
		
		@FindBy(css=".totalRow button")
		WebElement checkout;
		
		public Boolean VerifyOrderDisplay(String ProductName)
		{
		     Boolean b=orderprod.stream().anyMatch(p->p.getText().equalsIgnoreCase(ProductName));
return b;
		}
		
		public CheckoutPage GotoCheckoutPage()
		{
			checkout.click();
			//go to new page checkoutpage
			return new CheckoutPage(driver);
		}
}


