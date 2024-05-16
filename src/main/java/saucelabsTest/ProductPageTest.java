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
public class ProductPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CartCheckoutPage cartCheckOutPage;
	String fleeceJacketProductPrice = null;
	String onesieProductPrice = null;
	String jacketCartPageQuantity = null;
	private static final String JACKET_NAME = "jacket_product_name";
	private static final String ONESIE_NAME = "onesie_product_name";
	private static final String ORDER_COMPLETE_MESSAGE = "order_complete_message";

	@BeforeMethod
	public void beforeEach() throws IOException {
		driver.get(properties.getProperty("URL"));
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		productPage = new ProductPage(driver);
		cartCheckOutPage = new CartCheckoutPage(driver);
	}

	@Test(testName = "Test case 3 (Multiple scenarios Workflow)")
	public void multipleScenariosWorkflowTest() throws IOException {
		// Login using credentials
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();

		// Change the Product Sort to “Price (low to high)” on “Products” page
		productPage.selectLowHighFilter("Price (low to high)");

		// Assert if the Selected (Displayed) Item on the Product Sort is “Price (low to
		// high)”
		productPage.validateSeletedSortOption("Price (low to high)");

		// Click “Add to Cart” button for “Sauce Labs Fleece Jacket” and “Sauce Labs
		// Onesie”
		productPage.addFleeceJacketToCart();
		productPage.addOnesieToCart();

		// Check if “Add to Cart” button not visible for “Sauce Labs Fleece Jacket” and
		// “Sauce Labs Onesie”
		productPage.validateFleeceJacketAddButtonNotVisible();
		productPage.validateOnesieAddButtonNotVisible();

		// Check if “Remove” button enabled for “Sauce Labs Fleece Jacket” and “Sauce
		// Labs Onesie”
		productPage.validateFleeceJacketRemoveButtonEnable();
		productPage.validateOnesieRemoveButtonEnable();

		// Capture price of “Sauce Labs Fleece Jacket” from “Products” page
		fleeceJacketProductPrice = productPage.getProductPriceFromProductPage(properties.getProperty(JACKET_NAME));

		// Capture price of “Sauce Labs Onesie” from “Products” page
		onesieProductPrice = productPage.getProductPriceFromProductPage(properties.getProperty(ONESIE_NAME));

		// Capture value from “Cart Icon” on the top right and assert is its “2”.
		productPage.validateCartValue("2");

		// Click “Cart” icon
		productPage.goToCart();

		// Assert price values of product on product and cart page
		cartCheckOutPage.assertProductPrice(fleeceJacketProductPrice,
				cartCheckOutPage.getProductPriceFromCartPage(properties.getProperty(JACKET_NAME)),
				"Assert Jacket Price is same on product and cart page");
		cartCheckOutPage.assertProductPrice(onesieProductPrice,
				cartCheckOutPage.getProductPriceFromCartPage(properties.getProperty(ONESIE_NAME)),
				"Assert Onesie Price is same on product and cart page");

		// Click “Remove” button for “Sauce Labs Onesie” on “Your Cart“ page
		cartCheckOutPage.clickRemoveOnesieButton();

		// Capture quantity of “Sauce Labs Fleece Jacket” from “Your Cart“page
		jacketCartPageQuantity = cartCheckOutPage.getQuantityOfProduct(properties.getProperty(JACKET_NAME));

		// Assert quantity values from
		cartCheckOutPage.assertQuantityCount(jacketCartPageQuantity, cartCheckOutPage.getCartCount(),
				"Assert quantity is shown same");

		// Click “Checkout” button on “Your Cart“ page
		cartCheckOutPage.clickCheckOutButton();

		// Fill the “Checkout: Your Information” page (Random data).
		cartCheckOutPage.fillCheckoutForm();

		// Click “Continue” button
		cartCheckOutPage.clickContinueButton();

		// Capture “Item total” from “Checkout: Overview” page and Assert it with
		// product price
		cartCheckOutPage.assertProductPrice(fleeceJacketProductPrice,
				cartCheckOutPage.getProductPriceFromCartPage(properties.getProperty(JACKET_NAME)),
				"Assert Jacket Price is same on product and cart page");

		// Click “Finish” button on “Checkout: Overview” page
		cartCheckOutPage.clickFinishButton();

		// Capture “Thank you for your order” text from “Checkout Complete” page and
		// Assert it
		cartCheckOutPage.assertOrderCompleteMessage(properties.getProperty(ORDER_COMPLETE_MESSAGE),
				"Assert the Order Complete Message");

	}

}
