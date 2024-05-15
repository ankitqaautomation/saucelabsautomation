/**
 * 
 */
package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import commons.utilities.Report;
import commons.utilities.TESTSTATUS;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TakesScreenshot;
import java.io.File;

/**
 * @author ankitsharma 13 May 2024
 */
public class BaseClass {

	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected final Properties properties;
	protected final Properties XPATH;
	protected final Actions actions;
	protected final By cartCount = By.xpath("//div[@id='shopping_cart_container']/a/span");

	public BaseClass(WebDriver driver) throws IOException {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		this.properties = PropertyReader.getProperties("config");
		this.XPATH = PropertyReader.getProperties("xpath");
		this.actions = new Actions(driver);
	}

	public WebUtil getWebUtil() {
		return new WebUtil();
	}

	public String getPageURL() {
		return this.driver.getCurrentUrl();
	}

	public String getPageTitle() {
		return this.driver.getTitle();
	}

	public String getPageSource() {
		return this.driver.getPageSource();
	}
	
	public void refreshBrowser() {
		driver.navigate().refresh();
		printTestSteps("Refresh the browser");
	}

	public boolean verifyPageTitle(String title) {
		return this.driver.getTitle().equalsIgnoreCase(title);
	}

	/**
	 * 
	 * @param testStep : Manual test case steps
	 */
	public static final void printTestSteps(String testStep) {
		Reporter.log("TestSteps: " + testStep);
	}

	/**
	 * Validate the expected vs actual status
	 * 
	 * @param actualStatus
	 * @param message
	 * @param expectedStatus
	 */
	public static final void validate(boolean actualStatus, String message, boolean expectedStatus) {

		try {
			if (expectedStatus == actualStatus) {
				Reporter.log("Validate: " + message + "::" + "PASS");
			} else {
				Reporter.log("Validate: " + message + "::" + "FAIL");
				Assert.fail("message");
			}
		} catch (Exception e) {
			Report.showException(e);
		}
	}

	/**
	 * 
	 * @param title : read the the page title from config.properties
	 * @throws InterruptedException 
	 */
	public void closeTabUsingTitle(String title){
		// Get the Set of all the windows
		Set<String> windowHandles = driver.getWindowHandles();
		// Run the loop to close a specific window on the basis of title
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			driverWait(1000);
			if (driver.getTitle().equals(title)) {
				driver.close();
				printTestSteps("Close “" + title + "” tab");
				break;
			}
		}
	}

	/**
	 * 
	 * @param title : read the the page title from config.properties
	 */
	public void moveToTabUsingTitle(String title){
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			driverWait(1000);
			if (driver.getTitle().equals(title)) {
				printTestSteps("Navigate to “" + title + "” tab");
				break;
			}
		}
	}

	/**
	 * 
	 * @param driver
	 * @param screenshotName
	 * @throws InterruptedException 
	 */
	public static void captureScreenshot(WebDriver driver, String screenshotName){
		driverWait(2000);
		// Convert WebDriver object to TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Capture screenshot as File object
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// Specify the directory path where you want to save the screenshot
		String screenshotDirectory = System.getProperty("user.dir") + "/screenshots/";

		// Create the directory if it doesn't exist
		File theDir = new File(screenshotDirectory);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}

		// Specify the file path where you want to save the screenshot
		String filePath = screenshotDirectory + screenshotName + "_"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";

		// Copy the screenshot to the specified file path
		try {
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void driverWait(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void assertPriceValues(String price1, String price2, String message) {
		
		validate(price1.equalsIgnoreCase(price2), message, true);
		
	} 
	

}
