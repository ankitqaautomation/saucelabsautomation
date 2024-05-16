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
	private final By removeOnesieButton = By.id("remove-sauce-labs-onesie");
	private final By cartIcon = By.xpath("//span[@data-test='shopping-cart-badge']");
	private final By completeHeader = By.xpath("//h2[@data-test='complete-header']");
	
	
	
	
	public void clickCheckOutButton() throws IOException{
		getWebUtil().clickElement(checkOutButton);
		printTestSteps("Click “Checkout” button on “Your Cart” page");
	}
	
	
	public void clickContinueButton() throws IOException{
		getWebUtil().clickElement(continueButton);
		printTestSteps("Click “Continue” button");
	}
	
	public void clickFinishButton() throws IOException{
		getWebUtil().clickElement(finishButton);
		printTestSteps("Click “Finish” button on “Checkout: Overview” page");
	}
	
	public void clickRemoveOnesieButton() throws IOException{
		getWebUtil().clickElement(removeOnesieButton);
		printTestSteps("Click “Remove” button for “Sauce Labs Onesie” on “Your Cart“ page");
	}
	
	private void enterFirstName(String text) throws IOException{
		getWebUtil().enterText(firstnameTextBox, text);
	}
	
	private void enterLastName(String text) throws IOException{
		getWebUtil().enterText(lastnameTextBox, text);
	}
	
	
	private void enterZipCode(String text) throws IOException{
		getWebUtil().enterText(zipCodeTextBox, text);
	}
	
	public void fillCheckoutForm() throws IOException{
		enterFirstName(properties.getProperty("checkout_firstname"));
		enterLastName(properties.getProperty("checkout_lastname"));
		enterZipCode(properties.getProperty("checkout_zipcode"));
		printTestSteps("Fill the “Checkout: Your Information” page (Random data).");
	}
	
	public String getProductPriceFromCartPage(String productName) throws IOException{
		printTestSteps("Capture price of “"+productName+"” from “Cart” page");
		return getWebUtil().getText(By.xpath("//div[text()='"+productName+"']/ancestor::div[@data-test='inventory-item']//div[@data-test='inventory-item-price']"));
	}
	
	public String getQuantityOfProduct(String productName) throws IOException{
		printTestSteps("Capture price of “"+productName+"” from “Cart” page");
		return getWebUtil().getText(By.xpath("//div[text()='"+productName+"']/ancestor::div[@data-test='inventory-item']//div[@data-test='item-quantity']"));
	}
	
	public String getCartCount() throws IOException {
		printTestSteps("Capture cart count from “Cart” page");
		return getWebUtil().getText(cartIcon);
	}
	
	public String getOrderCompleteMessage() throws IOException {
		return getWebUtil().getText(completeHeader);
	}
	
	
	public void assertProductPrice(String expectedString, String actualString, String message) {
		compareStringValues(expectedString, actualString, message);
	}
	
	
	public void assertQuantityCount(String expectedString, String actualString, String message) {
		compareStringValues(expectedString, actualString, message);
	}
	
	public void assertOrderCompleteMessage(String expectedString,String message) throws IOException {
		compareStringValues(expectedString, getOrderCompleteMessage(), message);
	}
	
	
	
	
	
	
	
	
	
	
}
