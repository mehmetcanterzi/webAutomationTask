/**
 * 
 */
package tests;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import base.Base;

/**
 * @author can.terzi
 *
 */
public class TestWebAutomation extends Base {

	@Test
	public void TaskTest() throws InterruptedException, IOException {

		mp.goToLoginPage();
		lp.failedLogin("test@test.com", "password");
		mp.checkSuccessfulLogin();
		lp.moveToMainPage();
		mp.SearchProduct("Bilgisayar");
		rp.changeToSecondPage();
		rp.getProductInformations();
		rp.addProductToBasket();
		rp.moveToBasket();
		bp.comparePrices();
		bp.changeAmountToTwo();
		bp.removeProduct();

	}
}
