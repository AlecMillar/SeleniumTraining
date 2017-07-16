package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.DashboardPage;
import pages.LoginPage;

public class LoginTestPOM {
	WebDriver driver;
	String baseUrl = "http://sdettraining.com/trguitransactions/Account.aspx";
	
	@Test
	public void loginTestCase() {
		
		/* These actions are all on the login page
		 * driver.findElement(By.id("MainContent_txtUserName")).sendKeys("tim@testemail.com");
		 * driver.findElement(By.id("MainContent_txtPassword")).sendKeys("trpass");
		 * driver.findElement(By.id("MainContent_btnLogin")).click();
		 */
		
		LoginPage.typeUsername(driver, "tim@testemail.com");
		LoginPage.TypePassword(driver, "trpass");
		LoginPage.clickLogin(driver);
		
		
		// These actions are all on the dashboard page
		// String confirmation = driver.findElement(By.id("MainContent_lblid")).getText();
		String confirmation = DashboardPage.confirmLogin(driver);
		
		// This is an assertion
		assertTrue(confirmation.contains("Welcome back!"));
		
	}
	
	@Before
	public void setUp() {
		driver = utilities.DriverFactory.newDriver("chrome");
		driver.get(baseUrl);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
