package rahulshettyacademy.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public static   WebDriver driver;
	//global driver object is created so wat ever driver is passed it runs accordingly
	public LandingPage lp ;
	public static WebDriver IntializeDriver() throws IOException
	{
		//There is properties class , which decided which browser need to be used chrome,firefox,IE etc..
		Properties property = new Properties();
	//	FileInputStream fis= new FileInputStream("D:\\Users\\kousi\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\Resources\\Globalproperties.properties	");
	//AVoid hardcoding your local pc path bcoz the code wont run in other pc so the above line is replaced  by below
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//Resources//Globalproperties.properties");

		property.load(fis);
		String browsername = System.getProperty("browser")!=null? System.getProperty("browser"):property.getProperty("browser");
		
		if (browsername.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
if (browsername.contains("Headless"))
{
			options.addArguments("Headless");
}			
		 driver = new ChromeDriver(options);
driver.manage().window().setSize(new Dimension(1400,900)); //full screen for headlessmode		
		}
		/*else if (browsername.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		 driver = new GekoDriver();
		
		}
		
		else if (browsername.equalsIgnoreCase("IE"))
	{
	WebDriverManager.iedriver().setup();
	 driver = new IEDriver();
		}*/
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
}
	
	//Donot hardcode file path as we may use many files so pass filepath as arguments

		public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException
		{
			//Fileutil package has a method to read complete file and convert its content to string variable
			
			String StringContent = FileUtils.readFileToString(new File(filepath,"StandardCharset.UTF_8"));
		    // Now convert this String to Hashmap using jackson databind dependency so add it to ur pom using mvn repository
					ObjectMapper mapper = new ObjectMapper();
					List<HashMap<String,String>>  data = mapper.readValue(StringContent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
		}
	//Create a screenShot utility in basetest to capture all failed testcases. This is used in Extent Reports
		public String getScreenShot(String testcasename,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			//Now we need to store this source file to our location machine 
			File file = new File(System.getProperty("user.dir")+"//reports//"+ testcasename + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+ testcasename + ".png";
		}
		
	@BeforeMethod(alwaysRun =true)//bcoz of groupping in testng
	public LandingPage LaunchApplication() throws IOException
	{
		driver = IntializeDriver();
		//Now copy url invoking step here as this needs to be used in all testcases also there will be NO driver object in standalonetest code
		 lp = new LandingPage(driver);
		lp.Goto();
	return lp;
	}
	@AfterMethod(alwaysRun =true)
public void teardown()
{
	driver.close();
	}
	
}