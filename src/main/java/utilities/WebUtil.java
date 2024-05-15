/**
 * 
 */
package utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author ankitsharma 14 May 2024
 */
public class WebUtil {
	
	public String getText(WebDriver driver, By by) {
		return driver.findElement(by).getText();
	} 
	
	public WebElement getElement(WebDriver driver, By by) {
		return driver.findElement(by);
	} 
	
	public List<WebElement> getElementsList(WebDriver driver, By by) {
		return driver.findElements(by);
	}
	
	public void clickElement(WebDriver driver, By by) {
		 driver.findElement(by).click();
	}
	
	public void clickElementUntilVisible(WebDriver driver, By by) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by)).click();
	}
	
	public boolean getElementUntilVisible(WebDriver driver, By by) {
		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(by)).isEnabled();
	}
	
	public void enterText(WebDriver driver, By by,String text) {
		driver.findElement(by).sendKeys(text);
	}


}
