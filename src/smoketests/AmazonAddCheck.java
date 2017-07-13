package smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class AmazonAddCheck {
	
	public static void testAddToCart(WebDriver driver, String expected, String item) {
		String actual = null;
		
		try {
			actual = driver.findElement(By.id("huc-v2-order-row-confirm-text")).getText();
		} catch (NoSuchElementException e) {
			Assert.fail("ADD TO CART TEST FAILED");
		}
		if (actual.contains(expected)) {
			System.out.println(item + " ADDED TO CART");
		}
	}

}
