package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage  extends AbstractComponent {

		WebDriver driver;

		public CheckoutPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		 @FindBy(css="div.form-group .input.txt.text-validated")
		 WebElement countryfield;
		 
		 @FindBy(css=".action__submit")
		 WebElement submit;
		 
		 @FindBy(css=".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")
		 WebElement selectcountry;
		 
		 By results = By.cssSelector(".ta-results");
		 public void Selectcountry(String CountryName)
		 {
			 Actions s= new Actions(driver);
		        
		        s.sendKeys(countryfield, CountryName).build().perform();
		        waitforElementToAppear(By.cssSelector(".ta-results"));
		        selectcountry.click(); 	 	
		 }
		 public ConfirmationPage submitorder()
		 {
			  JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("arguments[0].click()", submit);
		        //submit.click();
		     return   new ConfirmationPage(driver);

		 }
}
