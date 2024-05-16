/**
 * 
 */
package saucelabsTest;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.utilities.PropertiesRead;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.SocialMedia;
import utilities.BaseClass;

/**
 * @author ankitsharma 13 May 2024
 */
public class SocialProfileTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void beforeEach() throws IOException {
		driver.get(properties.getProperty("URL"));
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(testName = "Bonus Test case 5", description = "Verify that user should be able to open social links")
	public void socialLinksTest() throws IOException {
		// Login using credentials
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();

		// Click “Social Media Link” icons at the bottom left of the footer.
		homePage.clickOnSocialMediaIcon(SocialMedia.TWITTER);
		homePage.clickOnSocialMediaIcon(SocialMedia.FACEBOOK);
		homePage.clickOnSocialMediaIcon(SocialMedia.LINKEDIN);

		// Closing Tabs
		homePage.closeTabUsingTitle(properties.getProperty("facebook_title"));
		homePage.closeTabUsingTitle(properties.getProperty("twitter_title"));

		// Navigate back to Swag Labs Tab
		homePage.moveToTabUsingTitle(properties.getProperty("page_title"));

		// Click Menu and Logout Button
		homePage.logout();

		// Closing Tabs
		homePage.closeTabUsingTitle(properties.getProperty("linkedIn_title"));

	}

}
