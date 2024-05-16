/**
 * 
 */
package saucelabsTest;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import utilities.BaseClass;
import utilities.PropertyReader;

/**
 * @author ankitsharma 13 May 2024
 */
public class TestBase {
	
	 	public WebDriver driver;
	    protected Properties properties;
	    protected Properties XPATH;

	    @BeforeClass
	    @Parameters({"browser"})
	    public void setupBeforeTest(String browser) throws IOException{
	    	if(browser.equalsIgnoreCase("firefox")) {
	    		this.driver = new FirefoxDriver();
	    	}else if(browser.equalsIgnoreCase("chrome")) {
	    		this.driver = new ChromeDriver();
	    	}
	    	driver.manage().window().maximize();	
	        this.properties = PropertyReader.getProperties("config");
	        this.XPATH = PropertyReader.getProperties("xpath");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	    
	    @AfterMethod
	    public void captureFailureScreenshot(ITestResult result) {
	    	
	    	if (result.getStatus()==2 && driver != null) {
	    		BaseClass.captureScreenshot(driver, result.getName());
	    	}
	    	
	    }
	    
	    

}
