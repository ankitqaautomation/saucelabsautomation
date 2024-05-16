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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import utilities.BaseClass;

/**
 * @author ankitsharma 13 May 2024
 */
public class HomePage extends BaseClass {

	By filter = By.xpath("//select[@class='product_sort_container']");
	By homepageHeading = By.xpath("//span[contains(text(),'Products')]");
	protected final By hamburgerMenuIcon = By.xpath("//button[@id='react-burger-menu-btn']"); 
	protected final By allItemsButton = By.xpath("//a[@id='inventory_sidebar_link']");
	protected final By logoutButton = By.xpath("//a[@id='logout_sidebar_link']");
	protected final By resetAppButton = By.xpath("//a[@id='reset_sidebar_link']");
	protected final By closeMenu = By.id("react-burger-cross-btn");
	// social media links in footer
	private final By facebookLink = By.xpath("//a[contains(text(),'Facebook')]");
	private final By linkedinLink = By.xpath("//a[contains(text(),'LinkedIn')]");
	private final By twitterLink = By.xpath("//a[contains(text(),'Twitter')]");

	public HomePage(WebDriver driver) throws IOException {
		super(driver);
	}

	public void logout() throws IOException{
		clickOnMenuIcon();
		clickLogout();
		printTestSteps("Logout Successfully");
	}

	public void resetAppState() throws IOException{
		clickOnMenuIcon();
		clickResetAppButton();
		closeMenu();
	}
	
    public void  validateHomePage(){
    	validate(getPageTitle().equalsIgnoreCase(properties.getProperty("page_title")), "User is logged in successfully", true);
    }
    
    
    public void goToAllItems() throws IOException{
    	clickOnMenuIcon();
    	clickOnAllItems();
    }
    
    
	// click on the All Items Button
	public void clickOnAllItems() throws IOException {
		getWebUtil().clickElement(allItemsButton);
		printTestSteps("Click “All Items” within the “Menu” Icon on top left of the header.");
	}
    

	// click on the Menu Button
	public void clickOnMenuIcon() throws IOException {
		getWebUtil().clickElement(hamburgerMenuIcon);
		printTestSteps("Click “Menu” Icon on top left of the header");
	}

	// click on the Logout Button
	public void clickLogout()throws IOException {
		getWebUtil().clickElement(logoutButton);
		printTestSteps("Click “Logout”");
	}

	// click on the Logout Button
	public void clickResetAppButton() throws IOException{
		getWebUtil().clickElement(resetAppButton);
		printTestSteps("Click “Reset App State” within the “Menu” Icon on top left of the header.");
	}

	// click on the Close Menu "X"
	public void closeMenu() throws IOException{
		getWebUtil().clickElement(closeMenu);
		printTestSteps("Close Menu");
	}

	// click on the Facebook icon
	public void clickFacebookLink()throws IOException {
		getWebUtil().clickElement(facebookLink);
		printTestSteps("Click “Facebook” icon at the bottom left of the footer");
	}

	// click on the Twitter icon
	public void clickTwitterLink() throws IOException{
		getWebUtil().clickElement(twitterLink);
		printTestSteps("Click “Twitter” icon at the bottom left of the footer");
	}

	// click on the LinkedIn icon
	public void clickLinkedinLink() throws IOException{
		getWebUtil().clickElement(linkedinLink);
		printTestSteps("Click “Linkedin” icon at the bottom left of the footer.");
	}

	// get the heading of the Home Page
	public String getPageHeading() throws IOException{
		return getWebUtil().getText(homepageHeading);
	}

	/**
	 * Pass one of the enum values TWITTER , FACEBOOK, LINKEDIN
	 * 
	 * @param name
	 * @throws InterruptedException
	 */
	public void clickOnSocialMediaIcon(SocialMedia name) throws IOException{

		switch (name.toString()) {
		case "TWITTER":
			clickTwitterLink();
			break;
		case "FACEBOOK":
			clickFacebookLink();
			break;
		case "LINKEDIN":
			clickLinkedinLink();
			break;
		default:
			Reporter.log("Wrong social link name passed, please check");
			break;
		}
	}

}
