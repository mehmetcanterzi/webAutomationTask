/**
 * 
 */
package base;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import pages.BasketPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ResultsPage;

/**
 * @author can.terzi
 *
 */
public class Base {

	
	String url = "https://www.gittigidiyor.com/";
	protected BasketPage bp;
	protected LoginPage lp;
	protected MainPage mp;
	protected ResultsPage rp;
	private WebDriver driver;
	
	@BeforeEach
	public void beforeTest() {
		initDriver();
		initPages();
	}
	
	protected void initPages() {
		bp = PageFactory.initElements(driver, BasketPage.class);
		lp = PageFactory.initElements(driver, LoginPage.class);
		mp = PageFactory.initElements(driver, MainPage.class);
		rp = PageFactory.initElements(driver, ResultsPage.class);
		
	}
	
	protected void initDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--start-maximized");
		options.addArguments(new String[] { "disable-infobars" });
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.addArguments("window-size=1920,1080");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to(url);
	}
	
	@AfterEach
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
