package com.SDETtraining.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.DriverFactory;

/*
 * This app will run a test search on Amazon for
 * four items (read from Excel)and add them to your cart.
 * It will then check the cart for the sub-total
 * cost of the order and display this value.
 * 
 */

/*
 * EXEL ENTREES USED FOR TEST (no quotes):
 *   	  A							 B
 * 	1	"aps"					"trash can"
 * 	2	"aps"					"electronics"
 * 	3	"electronics"			"iPhone Charger"
 * 	4	"local-services"		"backpack"
 */

public class AmazonSearch {

	WebDriver driver;
	String baseUrl = "https://www.amazon.com/";
	
	/*
	 * Must insert email and password to use
	 */
	private static String email = null;
	private static String password = null;

	// Test to search for items on amazon & add them to the cart.
	@Test (dataProvider = "getSearchData")
	public void testAddToCart(String searchFilter, String searchObject) {

		// Searches Amazon for requested item
		String Filter = "search-alias=" + searchFilter;
		new Select(driver.findElement(By.id("searchDropdownBox"))).selectByValue(Filter);
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchObject);
		driver.findElement(By.id("nav-search-submit-text")).submit();

		// Sends requested item to cart
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[3]/div[2]/div/div[4]/div[1]/div/ul/li[6]/div/div/div/div[2]/div[1]/div[1]/a/h2")).click();
		driver.findElement(By.id("add-to-cart-button")).submit();
		
		// Tests to make sure item was added to cart
		smoketests.AmazonAddCheck.testAddToCart(driver, "Added to Cart", searchObject);
	}

	// Instantiates driver, navigates to Amazon, and signs in.
	@BeforeClass
	public void beforeClass() {
		new utilities.DriverFactory();
		driver = DriverFactory.newDriver("chrome");
		driver.get(baseUrl);

		// Signs into Amazon
		driver.findElement(By.xpath("html/body/div[1]/header/div/div[1]/div[4]/div[8]/div[2]/a/span")).click();
		driver.findElement(By.id("ap_email")).sendKeys(email);
		driver.findElement(By.id("ap_password")).sendKeys(password);
		driver.findElement(By.id("signInSubmit")).click();
	}
	// Checks cart, retrieves order sub-total, and quits of browser.
	@AfterClass
	public void afterClass() {
		driver.findElement(By.id("nav-cart")).click();
		System.out.println("Order total: " + driver.findElement(By.xpath("html/body/div[1]/div[4]/div/div[2]/div/div[1]/div[1]/form/div[3]/div[2]/div/div[1]/p/span/span[2]")).getText());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	// Retrieves data from Excel sheet
	@DataProvider
	public String[][] getSearchData() {
		return datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/amazonsearch.xls");
	}

}
