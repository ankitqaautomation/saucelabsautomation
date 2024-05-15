package saucelabsTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import objectRepository.HomePage;
import objectRepository.LoginPage;
import utilities.BaseClass;

/**
 * 
 */

/**
 * @author ankitsharma 13 May 2024
 */
public class LoginPageTest extends InitializationHelper {

	LoginPage loginPage;
	HomePage homePage;

	@DataProvider(name = "loginCredentials")
	public Object[][] loginCredentials() {
		return new Object[][] { { properties.getProperty("username"), properties.getProperty("password") },
				{ properties.getProperty("locked_username"), properties.getProperty("password") }, };
	}

	@BeforeMethod
	public void beforeEach() throws IOException {
		driver.get(properties.getProperty("URL"));
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Test(testName = "Test case 1 (Successful Login and Logout)", description = "Verify that user should be successfully login and logout")
	public void loginAndLogoutTest() {
		// Login using credentials
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

		// Validate that HomePage is visible on successful login
		homePage.validateHomePage();

		// Click Menu and Logout Button
		homePage.logout();
	}

	@Test(testName = "Test case 2 (Failed Login)", description = "Verify that user should not be able to login with locked-out user")
	public void loginTestWithLockedUser() {
		// Login using credentials
		loginPage.login(properties.getProperty("locked_username"), properties.getProperty("password"));

		// Validate error message on invalid credentials
		loginPage.validateErrorMessageonLoginPage();
	}

	@Test(dataProvider = "loginCredentials", description = "Verify user should Log in Successfully Clicking button")
	public void loginUsingDataProvider(String username, String password) {
		// Open URL
		driver.get(properties.getProperty("URL"));

		// Login using credentials
		loginPage.login(username, password);

		// Validate login on the base of username
		if (username.equalsIgnoreCase("locked_out_user")) {
			loginPage.validateErrorMessageonLoginPage();
		} else {
			homePage.validateHomePage();
			homePage.logout();
		}
	}

}
