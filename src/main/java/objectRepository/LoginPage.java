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
     */
    public void login(String username, String password){
    	enterUsername(username);
    	enterPassword(password);
    	clickLogin();
    }
    
    
    
    /**
     * 
     * @param username : read from config.properties
     */
    public void enterUsername(String username){
        getWebUtil().enterText(driver, this.usernameTextBox, username);
        printTestSteps("Enter Login Credentials: Username: “"+username+"” ;");
    }
    
    /**
     * 
     * @param password : read from config.properties
     */
    public void enterPassword(String password){
        getWebUtil().enterText(driver, this.passwordTextBox, password);
        printTestSteps("Enter Login Credentials: Password");
    }
    
    /**
     * Click on the login button
     */
    public void clickLogin(){
        getWebUtil().clickElement(driver, loginButton);
        printTestSteps("Click “Login”");
    }
    

    public void  validateErrorMessageonLoginPage(){
    	validate(getErrorMessageText().equalsIgnoreCase(properties.getProperty("error_message")), "User should not be able to logged in with locked out user", true);
    }
    
    
    public WebElement getErrorMessage(){
        return driver.findElement(By.tagName("h3"));
    }
    public String getErrorMessageText(){
        return this.getErrorMessage().getText();
    }

}
