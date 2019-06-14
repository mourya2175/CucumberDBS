package stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {
	
	public static WebDriver driver;
	@Before
	public void startBrowser()
	{
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}
	
	@After
	public void closeBrowser(Scenario scenario) throws IOException
	{
		if(scenario.isFailed())
		{
			String Base64 = "data:image/gif;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			Reporter.addScreenCaptureFromPath(Base64);
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
					
		}
		driver.quit();
	}
	@Given("sample feature file is ready")
	public void sample_feature_file_is_ready() {
		System.out.println("I am in third step");
	}

	@When("I run the feature file")
	public void i_run_the_feature_file() {
	   System.out.println("I am in first step");
	}

	@Then("run should be successful")
	public void run_should_be_successful() {
		System.out.println("I am in second step");
	}
	
	@Given("^Navigate to application \"([^\"]*)\"$")
	public void navigate_to_application(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get(arg1);
		Reporter.addStepLog("URl is opened");
	}

	@When("^Search for \"([^\"]*)\"$")
	public void search_for(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("sb_form_q1234")).sendKeys(arg1);
		Reporter.addStepLog("Searched with : "+arg1);
	}
	
	@Given("^Navigate to application$")
	public void navigate_to_application(Map<String, String> fieldsList) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		driver.get(fieldsList.get("URL"));
	}

	@When("^Search for$")
	public void search_for(Map<String, String> fieldsList) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		driver.findElement(By.id("sb_form_q")).sendKeys(fieldsList.get("Keyword"));
	}

}
