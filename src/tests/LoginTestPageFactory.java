package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pagefactory.LoginPage;

public class LoginTestPageFactory {
	WebDriver driver;
	LoginPage LoginPage;
	public String email = "tim@testemail.com";
	public String password = "trpass";
	public String baseUrl = "http://sdettraining.com/trguitransactions/Account.aspx";
	
	@Test
	public void LoginTestCase() {
		LoginPage.loginToAccount(email, password);
	}
	
	@BeforeMethod
	public void setUp() {
		driver = utilities.DriverFactory.newDriver("chrome");
		driver.get(baseUrl);
		LoginPage = new LoginPage(driver);
	}
	@AfterMethod
	public void tearDown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
