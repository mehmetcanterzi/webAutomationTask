/**
 * 
 */
package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.Functions;
import utilities.UtilityMethods;

/**
 * @author can.terzi
 *
 */
public class ResultsPage {

	private Functions f;
	private Actions act;
	final static Logger logger = LogManager.getLogger(ResultsPage.class);

	public ResultsPage(WebDriver driver) {
		f = new Functions(driver);
		act = new Actions(driver);
	}

	@FindBy(xpath = "//span[contains(@id,'category-browse-navigation') and contains(text(),'Kategoriler')]")
	private WebElement spanCategories;

	@FindBy(xpath = "//a[text()='2' and contains(@href,'arama')]")
	private WebElement linkSecondPage;

	@FindBy(xpath = "(//li[contains(@id,'product_id')])[1]")
	private WebElement liFirstProduct;

	@FindBy(xpath = "(//div[contains(@class,'product-title-info')])[1]/*/span")
	private WebElement spanProductInfo;

	@FindBy(xpath = "//*[contains(@class,'price-txt')]")
	private WebElement spanPrice;

	@FindBy(xpath = "(//button[contains(text(),'Sepete Ekle')])[1]")
	private WebElement buttonAddToBasket;

	@FindBy(xpath = "//span[contains(text(),'Sepetim')]")
	private WebElement spanBasket;

	@FindBy(xpath = "//*[contains(text(),'Sepete Git')]")
	private WebElement spanGoToBasket;

	private void checkResultPageVisibility() throws InterruptedException {
		f.waitElement(spanCategories);
		logger.info("Search results seen successfully");
	}

	public void changeToSecondPage() throws InterruptedException {
		checkResultPageVisibility();
		f.scrollToElement(linkSecondPage);
		f.click(linkSecondPage);
	}

	public void getProductInformations() throws InterruptedException, IOException {

		f.waitElement(spanProductInfo);
		String productInfo = f.getTextOnElement(spanProductInfo);
		String price = f.getTextOnElement(spanPrice);

		UtilityMethods util = new UtilityMethods();
		util.clearTxt("files//productInfo.txt");
		util.writeToTxt("files//productInfo.txt", productInfo);
		util.writeToTxt("files//productInfo.txt", price);
		logger.info("Product informations written to file");

	}

	public void addProductToBasket() throws InterruptedException {

		f.scrollToElement(spanProductInfo);
		Thread.sleep(1000);
		act.moveToElement(spanProductInfo).build().perform();
		Thread.sleep(1000);
		f.click(buttonAddToBasket);
	}

	public void moveToBasket() throws InterruptedException {
		try {
			f.click(spanGoToBasket);
		} catch (Exception e) {
			Thread.sleep(5000);
			f.click(spanBasket);
		}
	}

}
