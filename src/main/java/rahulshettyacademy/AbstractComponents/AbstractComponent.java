package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink*='order']")
	WebElement order;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
PageFactory.initElements(driver, this);		
	}

	//All reusable codes stay here , use it in ur class file using inheritence concept
	public void waitforElementToAppear(By findby )
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	public void waitforWebElementToAppear(WebElement findby )
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}

	public void waitforElementToDisAppear(WebElement element )
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
public CartPage GotoCartPage()
{
	cart.click();
	CartPage cp= new CartPage(driver);
return cp;
}

public OrderPage GotoOrderPage()
{
	cart.click();
	OrderPage op= new OrderPage(driver);
return op;
}
}