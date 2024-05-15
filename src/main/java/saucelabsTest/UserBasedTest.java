/**
 * 
 */
package saucelabsTest;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import objectRepository.CartCheckoutPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;
import objectRepository.SocialMedia;
import utilities.BaseClass;

/**
 * @author ankitsharma 13 May 2024
 */
public class UserBasedTest extends InitializationHelper {

	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CartCheckoutPage cartCheckOutPage;

	@BeforeMethod
	public void beforeEach() throws IOException {
		driver.get(properties.getProperty("URL"));
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		productPage = new ProductPage(driver);
		cartCheckOutPage = new CartCheckoutPage(driver);
	}

	@Test(testName = "Test case 6 (Performance user)")
	public void performanceUserTest() {
		// Login using credentials
		loginPage.login(properties.getProperty("performance_username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();
		
		//Click “Add to Cart” button for “Sauce Labs Backpack” on “Products” page.
		productPage.addBagPackToCart();
		        
		//Click “Cart” icon on “Products” page.
		productPage.goToCart();
		        
		//Click “All Items” within the “Menu” Icon on top left of the header.
		homePage.goToAllItems();
		        
		//Click “Remove” button for “Sauce Labs Backpack” on “Products” page.
		productPage.removeBagPackFromCart();
		        
		//Click “Logout” within the “Menu” Icon on top left of the header.
		homePage.logout();
        
	}
	
	@Test(testName = "Test case 4 (Error User)")
	public void errorUserTest(){
		// Login using credentials
		loginPage.login(properties.getProperty("error_username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();
		
		//Click “Add to Cart” button for “Sauce Labs Bike Light” on “Products” page
		productPage.addBikeLightToCart();
		
		//Click “Cart” icon on “Products” page
		productPage.goToCart();
		
		//Click “Checkout” button on “Your Cart” page
		cartCheckOutPage.clickCheckOutButton();
		
		//Fill the “Checkout: Your Information” page (Random data).
		cartCheckOutPage.fillCheckoutForm();
		
		//Click “Continue” button
		cartCheckOutPage.clickContinueButton();
		
		//Click “Finish” button on “Checkout: Overview” page
		cartCheckOutPage.clickFinishButton();
		
		homePage.logout();
        
	}
	
	
	@Test(testName = "Test case 5 (Browser Refresh)")
	public void browserRefreshTest(){
		// Login using credentials
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();
		
		//Click “Add to Cart” button for “Sauce Labs Bolt T-Shirt” on “Products” page
		productPage.addTshirtToCart();
		
		//Click “Reset App State” within the “Menu” Icon on top left of the header
		homePage.resetAppState();
		
		//Refresh the browser
		homePage.refreshBrowser();
		
		//Check if “Remove” button not visible for “Sauce Labs Bolt T-Shirt
		productPage.validateTshirtRemoveButtonAfterReset();
		
		//Check if “Add to cart” button enabled for “Sauce Labs Bolt T-Shirt
		productPage.validateTshirtAddButtonAfterReset();
		
		//Validate that “Cart Icon” on the top right does not have any value
		productPage.validateEmptyCart();
		
		homePage.logout();
		
		
	}

}
