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
public class WebUtil extends BaseClass{
	
	/**
	 * @param driver
	 * @throws IOException
	 */
	public WebUtil(WebDriver driver) throws IOException  {
		super(driver);
	}

	
	public String getText(By by) {
		return getElementUntilVisible(by).getText();
	} 
	
	public WebElement getElement(By by) {
		return getElementUntilVisible(by);
	} 
	
	public List<WebElement> getElementsList(By by) {
		return driver.findElements(by);
	}
	
	public void clickElement(By by) {
		getElementUntilVisible(by).click();
	}
	
	private WebElement getElementUntilVisible(By by) {
		return new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(properties.getProperty("wait_seconds")))).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void enterText(By by,String text) {
		getElementUntilVisible(by).sendKeys(text);
	}


}
