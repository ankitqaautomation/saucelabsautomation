/**
 * 
 */
package objectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.internal.annotations.IDataProvidable;

import utilities.BaseClass;
import utilities.WebUtil;

/**
 * @author ankitsharma 13 May 2024
 */
public class ProductPage extends BaseClass {

	public ProductPage(WebDriver driver) throws IOException {
		super(driver);
	}

	String productName = null;
	private final By addToCartButtonForTshirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private final By addToCartButtonForBagPack = By.id("add-to-cart-sauce-labs-backpack");
	private final By addToCartButtonForBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
	private final By addToCartButtonForFleeceJacket = By.id("add-to-cart-sauce-labs-fleece-jacket");
	private final By addToCartButtonForOnesie = By.id("add-to-cart-sauce-labs-onesie");
	private final By removeButtonForBagPack = By.id("remove-sauce-labs-backpack");
	private final By removeButtonForTshirt = By.id("remove-sauce-labs-bolt-t-shirt");
	private final By removeButtonForFleeceJacket = By.id("remove-sauce-labs-fleece-jacket");
	private final By removeButtonForOnesie = By.id("remove-sauce-labs-onesie");
	private final By selectedSortOption = By.xpath("//span[@data-test='active-option']");
	private final By priceOfAllProductLocator = By.className("inventory_item_price");
	private final By cartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
	private final By selectSortDropdown = By.xpath("//select[@data-test='product-sort-container']");

	// add products to cart methods
	public void addBagPackToCart() throws IOException {
		getWebUtil().clickElement(addToCartButtonForBagPack);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Backpack” on “Products” page");
	}

	public void addTshirtToCart() throws IOException {
		getWebUtil().clickElement(addToCartButtonForTshirt);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Bolt T-Shirt” on “Products” page");
	}

	public void addBikeLightToCart() throws IOException {
		getWebUtil().clickElement(addToCartButtonForBikeLight);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Bike Light” on “Products” page");
	}

	public void addFleeceJacketToCart() throws IOException {
		getWebUtil().clickElement(addToCartButtonForFleeceJacket);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Fleece Jacket” on “Products” page");
	}

	public void addOnesieToCart() throws IOException {
		getWebUtil().clickElement(addToCartButtonForOnesie);
		printTestSteps("Click “Add to Cart” button for “Sauce Labs Onesie” on “Products” page");
	}

	// validation methods
	public void validateTshirtRemoveButtonAfterReset() throws IOException {
		validateButtonNotVisibleforProduct(removeButtonForTshirt,
				"Remove button is not visible for Sauce Labs Bolt T-Shirt");
	}

	public void validateTshirtAddButtonAfterReset() throws IOException {
		validateButtonEnableForProduct(addToCartButtonForTshirt,
				"Add to Cart button is enable for Sauce Labs Bolt T-Shirt");
	}

	public void validateFleeceJacketAddButtonNotVisible() throws IOException {
		validateButtonNotVisibleforProduct(addToCartButtonForFleeceJacket,
				"Add to Cart button is not visible for for Sauce Labs Bolt Fleece Jacket");
	}

	public void validateOnesieAddButtonNotVisible() throws IOException {
		validateButtonNotVisibleforProduct(addToCartButtonForOnesie,
				"Add to Cart button is not visible for for Sauce Labs Bolt Onesie");
	}

	public void validateFleeceJacketRemoveButtonEnable() throws IOException {
		validateButtonEnableForProduct(removeButtonForFleeceJacket,
				"Remove button is enable for Sauce Labs Fleece Jacket");
	}

	public void validateOnesieRemoveButtonEnable() throws IOException {
		validateButtonEnableForProduct(removeButtonForOnesie, "Remove button is enable for Sauce Labs Onesie");
	}

	private void validateButtonNotVisibleforProduct(By by, String message) throws IOException {
		boolean isButtonVisible = true;
		try {
			getWebUtil().getElement(by).isDisplayed();
		} catch (Exception e) {
			isButtonVisible = false;
		}
		validate(isButtonVisible, message, false);
	}

	private void validateButtonEnableForProduct(By by, String message) throws IOException {
		boolean isButtonVisible = false;
		try {
			isButtonVisible = getWebUtil().getElement(by).isEnabled();
		} catch (Exception e) {
		}
		validate(isButtonVisible, message, true);

	}

	public void validateEmptyCart() throws IOException {
		validate(getCartCount().isEmpty(), "Cart is shown Empty", true);
	}

	public void validateSeletedSortOption(String option) throws IOException {
		validate(getWebUtil().getText(selectedSortOption).equalsIgnoreCase(option),
				"Assert if the Selected (Displayed) Item on the Product Sort is “" + option + "”", true);
	}

	public void validateCartValue(String value) throws IOException {
		printTestSteps("Capture value from “Cart Icon” on the top right and assert is its “" + value + "”");
		validate(getCartCount().equalsIgnoreCase(value), "Assert the cart quantity value", true);

	}

	public void validatePriceSortedOrder() throws IOException {
		List<WebElement> priceElements = getWebUtil().getElementsList(priceOfAllProductLocator);
		List<Double> prices = new ArrayList<>();
		for (WebElement priceElement : priceElements){
			prices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
		}

		// Assert if prices are in ascending order
		List<Double> sortedPrices = new ArrayList<>(prices);
		Collections.sort(sortedPrices);
		validate(prices.equals(sortedPrices), "Prices are in ascending order", true);

	}

	// remove product methods
	public void removeBagPackFromCart() throws IOException {
		getWebUtil().clickElement(removeButtonForBagPack);
		printTestSteps("Click “Remove” button for “Sauce Labs Backpack” on “Products” page.");
	}

	// general page methods
	public void goToCart() throws IOException {
		getWebUtil().clickElement(cartIcon);
		printTestSteps("Click “Cart” icon on “Products” page");

	}

	public String getProductPriceFromProductPage(String productName) throws IOException {
		printTestSteps("Capture price of “" + productName + "” from “Products” page");
		return getWebUtil().getText(By.xpath("//div[text()='" + productName
				+ "']/ancestor::div[@data-test='inventory-item-description']//div[@data-test='inventory-item-price']"));
	}

	private String getCartCount() throws IOException {
		return getWebUtil().getText(cartIcon);
	}

	public void selectLowHighFilter(String option) throws IOException {

		getWebUtil().clickElement(selectSortDropdown);

		List<WebElement> allOptions = getWebUtil().getElementsList(By.cssSelector("select option"));

		for (int i = 0; i < allOptions.size(); i++) {

			if (allOptions.get(i).getText().contains(option)) {

				allOptions.get(i).click();

				break;

			}

		}

	}

}
