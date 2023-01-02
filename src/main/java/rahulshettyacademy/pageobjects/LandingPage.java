package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
//Pageobject class contains webelements and actions (methods) of the webpage , Main program will access these components from here.
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement useremail = driver.findElement(By.id("userEmail")).sendKeys("redmond@gmail.com");
//The above can be written in annotation format to avoid line of code using PageFactory design
// how this below code knows driver as we cut driver.findelementby code ,
// so we use pagefactory.initelements method it intialize all elements by using
// driver passed as arguments.

	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(id = "userPassword")
	WebElement pass;

	@FindBy(id = "login")
	WebElement login;
//wrong userid.pwd error message "ng-tns-c4-8 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error"
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public void Goto()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
public String GetErrormessage()
{
	waitforWebElementToAppear(errormessage);
	return errormessage.getText();
}
	public ProductPage loginApplication(String email, String password)
	{
		useremail.sendKeys(email);
		pass.sendKeys(password);
		login.click();
		ProductPage pp= new ProductPage(driver); 
		return pp;
		//create next page object inside the method where we know it takes you to next page
		//and return the obj and change the return type of the method
		//make change in main code accordingly
	}

}