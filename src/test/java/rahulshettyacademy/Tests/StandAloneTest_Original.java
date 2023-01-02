package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_Original {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().window().maximize();
driver.get("https://rahulshettyacademy.com/client");
String productname = "IPHONE 13 PRO";
//login to website and add one product to cart 
driver.findElement(By.id("userEmail")).sendKeys("redmond@gmail.com");
driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
driver.findElement(By.id("login")).click();

WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname))
.findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body  button:last-of-type")).click();
//Check the toast text appeard like added to cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        //in above code , we included driver.findelement inside invisiblity bcoz its taking lot of time in this step- performance issue
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        List<WebElement> cartprod = driver.findElements(By.cssSelector("div.cartSection h3"));
        Boolean b=cartprod.stream().anyMatch(p->p.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(true);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        WebElement countryfield = driver.findElement(By.cssSelector("div.form-group .input.txt.text-validated"));
        Actions s= new Actions(driver);
        
        s.sendKeys(countryfield, "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
        driver.findElement(By.cssSelector(".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")).click(); 	 	
        Thread.sleep(3000);
       // driver.findElement(By.cssSelector(".action__submit")).click(); 	
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        WebElement ele = driver.findElement(By.cssSelector(".action__submit"));

        jse.executeScript("arguments[0].click()", ele);

        

        Thread.sleep(1000);

        String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
        String Expectedmessage = "THANKYOU FOR THE ORDER.";
        Assert.assertEquals(message, Expectedmessage);
	}

}
