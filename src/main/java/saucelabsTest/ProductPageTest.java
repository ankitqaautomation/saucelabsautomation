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
import utilities.BaseClass;

/**
 * @author ankitsharma 13 May 2024
 */
public class ProductPageTest extends InitializationHelper {
	
	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CartCheckoutPage cartCheckOutPage;
	String fleeceJacketProductPrice = null;
	String onesieProductPrice = null;
	private static final String JACKET_PRODUCT = "Sauce Labs Fleece Jacket";
	private static final String ONESIE_PRODUCT = "Sauce Labs Onesie";
	
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
		
		//Change the Product Sort to “Price (low to high)” on “Products” page
		productPage.selectLowHighFilter("Price (low to high)");
		        
		//Assert if the Selected (Displayed) Item on the Product Sort is “Price (low to high)”
		productPage.validateSeletedSortOption("Price (low to high)");
		        
		//Click “Add to Cart” button for “Sauce Labs Fleece Jacket” and “Sauce Labs Onesie”
		productPage.addFleeceJacketToCart();
		productPage.addOnesieToCart();
		        
		//Check if “Add to Cart” button not visible for “Sauce Labs Fleece Jacket” and “Sauce Labs Onesie”
		productPage.validateFleeceJacketAddButtonNotVisible();
		productPage.validateOnesieAddButtonNotVisible();
		        
		//Check if “Remove” button enabled for “Sauce Labs Fleece Jacket” and “Sauce Labs Onesie”
		productPage.validateFleeceJacketRemoveButtonEnable();
		productPage.validateOnesieRemoveButtonEnable();
		
		//Capture price of “Sauce Labs Fleece Jacket” from “Products” page
		fleeceJacketProductPrice = productPage.getProductPriceFromProductPage(JACKET_PRODUCT);
		
		//Capture price of “Sauce Labs Onesie” from “Products” page
		onesieProductPrice = productPage.getProductPriceFromProductPage(ONESIE_PRODUCT);
		
		//Capture value from “Cart Icon” on the top right and assert is its “2”.
		productPage.validateCartValue("2");
		
		//Click “Cart” icon
		productPage.goToCart();
		
		//Assert price values of product on product and cart page
		BaseClass.assertPriceValues(fleeceJacketProductPrice, cartCheckOutPage.getProductPriceFromCartPage(JACKET_PRODUCT), "Assert Jacket Price is same on product and cart page");
		BaseClass.assertPriceValues(onesieProductPrice, cartCheckOutPage.getProductPriceFromCartPage(ONESIE_PRODUCT), "Assert Onesie Price is same on product and cart page");
		
		//Click “Remove” button for “Sauce Labs Onesie” on “Your Cart“ page
		cartCheckOutPage.clickRemoveOnesieButton();
		
		
        
	}
	

}
