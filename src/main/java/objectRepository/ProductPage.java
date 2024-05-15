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
	private final By selectedSortOption = By.xpath("//span[@data-test='active-option']");
	private final By priceOfAllProductLocator = By.className("inventory_item_price");
	

	private final By cartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
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
		validateButtonEnableForProduct(removeButtonForOnesie, "Remove button is enable for Sauce Labs Onesie");
	}

	public String getProductPriceFromProductPage(String productName) {
		printTestSteps("Capture price of “" + productName + "” from “Products” page");
		driverWait(500);
		return getWebUtil().getText(driver, By.xpath("//div[text()='" + productName
				+ "']/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_pricer']"));
	}

	private void validateButtonNotVisibleforProduct(By by, String message) {
		boolean isButtonVisible = true;
		try {
			getWebUtil().getElementUntilVisible(driver, by).isDisplayed();
		} catch (Exception e) {
			isButtonVisible = false;
		}
		validate(isButtonVisible, message, false);
	}

	private void validateButtonEnableForProduct(By by, String message) {
		boolean isButtonVisible = false;
		try {
			isButtonVisible = getWebUtil().getElementUntilVisible(driver, by).isEnabled();
		} catch (Exception e) {
		}
		validate(isButtonVisible, message, true);

	}

	private String getCartCount() {
		return getWebUtil().getText(driver, cartIcon);
	}

	public void validateEmptyCart() {
		validate(getCartCount().isEmpty(), "Cart is shown Empty", true);
	}

	public void selectLowHighFilter(String option) {

		getWebUtil().clickElement(driver, selectSortDropdown);

		List<WebElement> allOptions = getWebUtil().getElementsList(driver, By.cssSelector("select option"));

		for (int i = 0; i < allOptions.size(); i++) {

			if (allOptions.get(i).getText().contains(option)) {

				allOptions.get(i).click();

				break;

			}

		}

	}

	
	public void validateSeletedSortOption(String option) {
		validate(getWebUtil().getText(driver, selectedSortOption).equalsIgnoreCase(option), "Assert if the Selected (Displayed) Item on the Product Sort is “"+option+"”", true);
	}
	
	public void validateCartValue(String value) {
		printTestSteps("Capture value from “Cart Icon” on the top right and assert is its “"+value+"”");
		validate(getCartCount().equalsIgnoreCase(value), "", true);
		
	}
	

	public void validatePriceSortedOrder() {
        List<WebElement> priceElements = getWebUtil().getElementsList(driver, priceOfAllProductLocator);
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            prices.add(Double.parseDouble(priceElement.getText().replace("$", "")));
        }

        // Assert if prices are in ascending order
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        validate(prices.equals(sortedPrices), "Prices are in ascending order", true);

	}


	
	
	
	
}
