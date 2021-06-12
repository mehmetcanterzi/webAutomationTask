/**
 * 
 */
package pages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
public class BasketPage{

	private Functions f;
	private Actions act;
	final static Logger logger = LogManager.getLogger(BasketPage.class);

	public BasketPage(WebDriver driver) {
		f = new Functions(driver);
		act = new Actions(driver);
	}

	@FindBy(xpath = "//div[contains(@class,'total-price')]/strong")
	private WebElement strongTotalPrice;

	@FindBy(xpath = "//select[contains(@class,'amount')]")
	private WebElement selectAmount;

	@FindBy(xpath = "//option[contains(@value,'2')]")
	private WebElement optionAmount2;

	@FindBy(xpath = "(//a[contains(@class,'btn-delete btn-update-item')])[1]/i")
	private WebElement buttonDelete;

	@FindBy(xpath = "//li[contains(@class,'total-price-sticky')]/div[1]")
	private WebElement divTotalProduct;
	
	@FindBy(xpath = "//h1[contains(text(),'Alýþveriþ Sepetim')]")
	private WebElement labelMyBasket;
	
	private void checkBasketVisibility() throws InterruptedException {
		f.waitElement(labelMyBasket);
		logger.info("My basket page seen successfully");
	}

	public void comparePrices() throws InterruptedException, FileNotFoundException {
		checkBasketVisibility();
		String currentPrice = f.getTextOnElement(strongTotalPrice);
		UtilityMethods util = new UtilityMethods();
		ArrayList<String> info = util.getProductInformations("files//productInfo.txt");
		String price = info.get(1);
		try {
			assertEquals(currentPrice, price);
			logger.info("Price comparison is successful");
		} catch (Exception e) {
			logger.error("Price comparison failed");
		}		
	}
	
	private void selectAmountTwo() throws InterruptedException {
		f.click(selectAmount);
		f.click(optionAmount2);
	}
	
	public void changeAmountToTwo() throws InterruptedException {
		selectAmountTwo();
		String amount = f.getTextOnElement(divTotalProduct);
		try {
			assertEquals("Ürün Toplamý (2 Adet)", amount);
			logger.info("Amount successfully changed to 2");
		} catch (Exception e) {
			logger.error("Amount Change failed");
		}
		
	}
	
	public void removeProduct() throws InterruptedException {
		f.click(buttonDelete);
		logger.info("Pressed Delete button for product");
		String amount = f.getTextOnElement(divTotalProduct);		
		try {
			assertEquals("Ürün Toplamý (0 Adet)", amount);		
			logger.info("Basket successfully cleared");
		} catch (Exception e) {
			logger.error("Delete product failed. Basket is not empty");
		}
	}

	
}
