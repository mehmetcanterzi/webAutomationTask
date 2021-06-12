/**
 * 
 */
package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import base.Functions;

/**
 * @author can.terzi
 *
 */
public class LoginPage {

	final static Logger logger = LogManager.getLogger(LoginPage.class);
	private Functions f;

	public LoginPage(WebDriver driver) {
		f = new Functions(driver);
	}

	@FindBy(xpath = "//*[text()='Üye Giriþi']")
	private WebElement labelLoginPage;

	@FindBy(xpath = "//span[contains(text(),'Lütfen kullanýcý adýnýzý')]/preceding::input[1]")
	private WebElement inputUsername;

	@FindBy(xpath = "//*[contains(text(),'Þifreniz')]/following::input[1]")
	private WebElement inputPassword;

	@FindBy(xpath = "//iframe[contains(@title,'reCAPTCHA')]")
	private WebElement frameCaptcha;

	@FindAll({ @FindBy(xpath = "//input[contains(@type,'submit')]"),
			@FindBy(xpath = "//input[contains(@value,'Giriþ Yap')]") })
	private WebElement buttonLogin;

	@FindBy(xpath = "//a[contains(@class,'logo_gg imglink')]")
	private WebElement linkMainPage;

	private void enterUsername(String username) throws InterruptedException {
		f.write(inputUsername, username);
	}

	private void enterPassword(String password) throws InterruptedException {
		f.write(inputPassword, password);
	}

	private void clickLogin() throws InterruptedException {
		f.click(buttonLogin);
	}

	private void checkLoginPageVisibility() throws InterruptedException {
		f.waitElement(labelLoginPage);
		logger.info("Login page seen successfully");
	}

	public void failedLogin(String username, String password) throws InterruptedException {
		checkLoginPageVisibility();
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public void moveToMainPage() throws InterruptedException {
		f.click(linkMainPage);
	}

}
