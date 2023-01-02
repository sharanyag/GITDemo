package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinitions",
monochrome=true,plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
//extends AbstractTestNGCucumberTests is not required if you run for junit testrunner
//it is only needed for testNG testrunner
	
	//NO CONTENT IN BODY
}
