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

/**
 * @author ankitsharma 13 May 2024
 */
public class ProductPageTest extends InitializationHelper {
	
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

	@Test(testName = "Test case 3 (Multiple scenarios Workflow)")
	public void multipleScenariosWorkflowTest() {
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
	

}
