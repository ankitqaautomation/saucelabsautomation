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
public class ProductPage extends BaseClass {

	public ProductPage(WebDriver driver) throws IOException {
		super(driver);
	}

	private final By productNameXpath = By.xpath("//div[@data-test='inventory-item-name']");
	private final By addToCartButtonForTshirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private final By addToCartButtonForBagPack = By.id("add-to-cart-sauce-labs-backpack");
	private final By addToCartButtonForBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
	private final By addToCartButtonForFleeceJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");
	private final By addToCartButtonForOnesie = By.id("add-to-cart-sauce-labs-onesie");
	private final By removeButtonForBagPack = By.id("remove-sauce-labs-backpack");
	private final By removeButtonForTshirt = By.id("remove-sauce-labs-bolt-t-shirt");        
	private final By removeButtonForFleeceJacket = By.id("remove-sauce-labs-fleece-jacket");
	private final By removeButtonForOnesie = By.id("remove-sauce-labs-onesie");
	
	private final By cartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
	private final By productPrice = By.className("inventory_details_price");
	By filter = By.xpath("//select[@class='product_sort_container']");
	By pageHeading = By.xpath("//span[contains(text(),'Products')]");
	private final By selectSortDropdown = By.xpath("//select[@data-test='product-sort-container']");

	public void addBagPackToCart() {
		getWebUtil().clickElement(driver, addToCartButtonForBagPack);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Backpack” on “Products” page");
	}

	public void addTshirtToCart() {
		getWebUtil().clickElement(driver, addToCartButtonForTshirt);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Bolt T-Shirt” on “Products” page");
	}

	public void addBikeLightToCart() {
		getWebUtil().clickElement(driver, addToCartButtonForBikeLight);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Bike Light” on “Products” page");
	}
	
	public void addFleeceJacketToCart() {
		getWebUtil().clickElement(driver, addToCartButtonForFleeceJacket);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Fleece Jacket” on “Products” page");
	}
	
	public void addOnesieToCart() {
		getWebUtil().clickElement(driver, addToCartButtonForOnesie);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Onesie” on “Products” page");
	}

	public void goToCart() {
		getWebUtil().clickElement(driver, cartIcon);
		printTestSteps("Click “Cart” icon on “Products” page");

	}

	public void removeBagPackFromCart() {
		getWebUtil().clickElement(driver, removeButtonForBagPack);
		printTestSteps("Click “Remove” button for “Sauce Labs Backpack” on “Products” page.");
	}

	public void validateTshirtRemoveButtonAfterReset() {
		validateButtonNotVisibleforProduct(removeButtonForTshirt,
				"Remove button is not visible for Sauce Labs Bolt T-Shirt");
	}

	public void validateTshirtAddButtonAfterReset() {
		validateButtonEnableForProduct(addToCartButtonForTshirt,
				"Add to Cart button is enable for Sauce Labs Bolt T-Shirt");
	}
	
	public void validateFleeceJacketAddButtonNotVisible() {
		validateButtonNotVisibleforProduct(addToCartButtonForFleeceJacket,
				"Add to Cart button is not visible for for Sauce Labs Bolt Fleece Jacket");
	}
	
	public void validateOnesieAddButtonNotVisible() {
		validateButtonNotVisibleforProduct(addToCartButtonForOnesie,
				"Add to Cart button is not visible for for Sauce Labs Bolt Onesie");
	}
	
	public void validateFleeceJacketRemoveButtonEnable() {
		validateButtonEnableForProduct(removeButtonForFleeceJacket,
				"Remove button is enable for Sauce Labs Fleece Jacket");
	}
	
	public void validateOnesieRemoveButtonEnable() {
		validateButtonEnableForProduct(removeButtonForOnesie,
				"Remove button is enable for Sauce Labs Onesie");
	}
	
	
	

	private void validateButtonNotVisibleforProduct(By by, String message) {
		boolean isButtonVisible = true;
		try {
			getWebUtil().getElement(driver, by);
		} catch (Exception e) {
			isButtonVisible = false;
		}
		validate(isButtonVisible, message, false);
	}

	private void validateButtonEnableForProduct(By by, String message) {
		boolean isButtonVisible = false;
		try {
			isButtonVisible = getWebUtil().getElementUntilVisible(driver, by);
		} catch (Exception e) {
		}
		validate(isButtonVisible, message, true);
		
		
	}

	
	
	public String getCartCount() {
		return getWebUtil().getText(driver, cartIcon);
	}

	public void validateEmptyCart() {
		validate(getCartCount().isEmpty(), "Cart is shown Empty", true);
	}

	public void selectLowHighFilter() {

		getWebUtil().clickElement(driver, selectSortDropdown);

		List<WebElement> allOptions = getWebUtil().getElementsList(driver, By.cssSelector("select option"));

		String option = "Price (low to high)";

		for (int i = 0; i < allOptions.size(); i++) {

			if (allOptions.get(i).getText().contains(option)) {

				allOptions.get(i).click();

				System.out.println("clicked");
				break;

			}

		}

	}

	public String getProductName() {
		return getWebUtil().getText(driver, productNameXpath);
	}

	public String getProductPrice() {
		String price = getWebUtil().getText(driver, productPrice);
		return price.substring(1);
	}

	public List<WebElement> getCartCountList() {
		return getWebUtil().getElementsList(driver, cartCount);
	}

	// get the filter value
	public String getAppliedFilter() {
		return getWebUtil().getText(driver, By.xpath("//div[@class='right_component']/span/span"));
	}

	// get the heading of the Product Page
	public String getPageHeading() {
		return getWebUtil().getText(driver, pageHeading);
	}

	// go to a product detail page
	public void clickOnProductName(String productName) {
		getWebUtil().clickElement(driver,
				By.xpath(XPATH.getProperty("PRODUCT_NAME_START") + productName + XPATH.getProperty("XPATH_END")));
	}

	// add to cart locator using index
	public WebElement getAddToCart(int index) {
		return getWebUtil().getElement(driver,
				By.xpath(XPATH.getProperty("ADD_TO_CART_START") + index + XPATH.getProperty("ADD_TO_CART_END")));
	}

	// add a product to cart
	public void clickOnAddToCart(int index) {
		this.getAddToCart(index).click();
	}

	// remove from cart locator using index
	public WebElement getRemoveFromCart(int index) {
		return getWebUtil().getElement(driver, By.xpath(
				XPATH.getProperty("REMOVE_FROM_CART_START") + index + XPATH.getProperty("REMOVE_FROM_CART_END")));
	}

	// remove a product from cart
	public void clickOnRemoveFromCart(int index) {
		this.getRemoveFromCart(index).click();
	}

}
