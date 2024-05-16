/**
 * 
 */
package objectRepository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utilities.BaseClass;

/**
 * @author ankitsharma 13 May 2024
 */
public class LoginPage extends BaseClass {
	
	By usernameTextBox = By.id("user-name");
    By passwordTextBox = By.name("password");
    By loginButton = By.className("submit-button");

    public LoginPage(WebDriver driver) throws IOException{
        super(driver);
    }
    
    /**
     * 
     * @param username
     * @param password
     * @throws IOException 
     */
    public void login(String username, String password) throws IOException{
    	enterUsername(username);
    	enterPassword(password);
    	clickLogin();
    }
    
    
    
    /**
     * 
     * @param username : read from config.properties
     * @throws IOException 
     */
    public void enterUsername(String username) throws IOException{
        getWebUtil().enterText(usernameTextBox, username);
        printTestSteps("Enter Login Credentials: Username: “"+username+"” ;");
    }
    
    /**
     * 
     * @param password : read from config.properties
     * @throws IOException 
     */
    public void enterPassword(String password) throws IOException{
        getWebUtil().enterText(passwordTextBox, password);
        printTestSteps("Enter Login Credentials: Password");
    }
    
    /**
     * Click on the login button
     * @throws IOException 
     */
    public void clickLogin() throws IOException{
        getWebUtil().clickElement(loginButton);
        printTestSteps("Click “Login”");
    }
    

    public void  validateErrorMessageonLoginPage() throws IOException{
    	validate(getErrorMessageText().equalsIgnoreCase(properties.getProperty("error_message")), "User should not be able to logged in with locked out user", true);
    }
    
    
    public WebElement getErrorMessage() throws IOException{
        return getWebUtil().getElement(By.tagName("h3"));
    }
    public String getErrorMessageText() throws IOException{
        return this.getErrorMessage().getText();
    }

}
