/**
 * 
 */
package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author can.terzi
 *
 */
public class Functions {

	private WebDriver driver;
	private WebDriverWait wait;

	public Functions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
	}

	public void click(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void write(WebElement element, String text) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	public void clear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}

	public void scrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,-document.body.scrollHeight)");

	}

	public void scrollToElement(WebElement element) throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public void waitElement(WebElement element) throws InterruptedException {		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getTextOnElement(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		String text = "";		
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		text = element.getText();
		return text;
	}

	public void waitElementToDisappear(WebElement element) throws InterruptedException {		
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
}
