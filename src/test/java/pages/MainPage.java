/**
 * 
 */
package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.Functions;

/**
 * @author can.terzi
 *
 */
public class MainPage {

	private Functions f;
	private Actions act;
	final static Logger logger = LogManager.getLogger(MainPage.class);

	public MainPage(WebDriver driver) {
		f = new Functions(driver);
		act = new Actions(driver);
	}

	@FindBy(xpath = "//div[contains(@title,'Giriþ Yap')]")
	private WebElement divLogin;

	@FindBy(xpath = "//span[contains(text(),'Giriþ Yap')]")
	private WebElement buttonLogin;

	@FindBy(xpath = "//img[contains(@src,'anasayfa/nucleus') and @alt='GittiGidiyor']")
	private WebElement imgMainPage;

	@FindBy(xpath = "//input[contains(@placeholder,'Keþfetmeye Bak')]")
	private WebElement inputSearch;

	@FindBy(xpath = "//span[contains(text(),'BUL')]")
	private WebElement buttonSearch;

	@FindBy(xpath = "//div[contains(text(),'Hesabým')]")
	private WebElement divMyAccount;

	@FindBy(xpath = "//span[contains(text(),'Kapat')]")
	private WebElement spanClose;

	private void closeCookies() throws InterruptedException {
		f.click(spanClose);
	}

	private void hoverMouseLoginArea() {
		act.moveToElement(divLogin).build().perform();
	}

	private void clickLoginButton() throws InterruptedException {
		f.click(buttonLogin);
	}

	private void checkMainPageVisibility() throws InterruptedException {
		f.waitElement(imgMainPage);
		logger.info("Main Page seen successfully");
	}

	private void fillSearchValue(String text) throws InterruptedException {
		f.write(inputSearch, text);
	}

	private void clickSearchButton() throws InterruptedException {
		f.click(buttonSearch);
	}

	public void goToLoginPage() throws InterruptedException {
		checkMainPageVisibility();
		closeCookies();
		hoverMouseLoginArea();
		clickLoginButton();

	}

	public void checkSuccessfulLogin() throws InterruptedException {
		try {
			f.waitElement(divMyAccount);
		} catch (Exception e) {
			logger.error("Login Failed");
		}
	}

	public void SearchProduct(String text) throws InterruptedException {
		checkMainPageVisibility();
		fillSearchValue(text);
		clickSearchButton();

	}

}
