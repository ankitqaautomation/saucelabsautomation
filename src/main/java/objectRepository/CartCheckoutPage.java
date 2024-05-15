/**
 * 
 */
package objectRepository;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utilities.BaseClass;
import utilities.WebUtil;

/**
 * @author ankitsharma 13 May 2024
 */
public class CartCheckoutPage extends BaseClass {

	/**
	 * @param driver
	 * @throws IOException
	 */
	public CartCheckoutPage(WebDriver driver) throws IOException {
		super(driver);
	}

	private final By checkOutButton = By.id("checkout");
	private final By firstnameTextBox = By.id("first-name");
	private final By lastnameTextBox = By.id("last-name");
	private final By zipCodeTextBox = By.id("postal-code");
	private final By continueButton = By.id("continue");
	private final By finishButton = By.id("finish");
	
	
	
	public void clickCheckOutButton() {
		getWebUtil().clickElement(driver, checkOutButton);
		printTestSteps("Click “Checkout” button on “Your Cart” page");
	}
	
	
	public void clickContinueButton() {
		getWebUtil().clickElement(driver, continueButton);
		printTestSteps("Click “Continue” button");
	}
	
	public void clickFinishButton() {
		getWebUtil().clickElement(driver, finishButton);
		printTestSteps("Click “Finish” button on “Checkout: Overview” page");
	}
	
	private void enterFirstName(String text) {
		getWebUtil().enterText(driver, firstnameTextBox, text);
	}
	
	private void enterLastName(String text) {
		getWebUtil().enterText(driver, lastnameTextBox, text);
	}
	
	
	private void enterZipCode(String text) {
		getWebUtil().enterText(driver, zipCodeTextBox, text);
	}
	
	public void fillCheckoutForm() {
		enterFirstName(properties.getProperty("checkout_firstname"));
		enterLastName(properties.getProperty("checkout_lastname"));
		enterZipCode(properties.getProperty("checkout_zipcode"));
		printTestSteps("Fill the “Checkout: Your Information” page (Random data).");
	}
	
	
	
	
	
	
	
	
	
}
