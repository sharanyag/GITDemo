package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//Pageobject class contains webelements and actions (methods) of the webpage , Main program will access these components from here.
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productby = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body  button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	By animation = By.cssSelector(".ng-animating");

	public List<WebElement> getProductlist() {
		waitforElementToAppear(productby);
		return products;
	}

	public WebElement getproductname(String ProductName) {
		WebElement prod = getProductlist().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void AddprodToCart(String ProductName) {
		WebElement prod = getproductname(ProductName);
		prod.findElement(addToCart).click();
		waitforElementToAppear(toastmessage);
		waitforElementToDisAppear(spinner);

	}
}