package com.SDETtraining.ams;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	// Initialization of static parameters
	WebDriver driver;
	String baseUrl = "http://sdettraining.com/trguitransactions/Account.aspx";
	String email = "tim@testemail.com";
	String password = "trpass";
	int records = email.length();

	@Test
	public void testLogin() {
		driver.findElement(By.id("MainContent_txtUserName")).clear();
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(email);
		driver.findElement(By.id("MainContent_txtPassword")).clear();
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
		driver.findElement(By.id("MainContent_btnLogin")).click();

		String result = driver.findElement(By.id("MainContent_lblid")).getText();
		if (result.contains("Welcome back!")) {
			System.out.println("TEST PASSED");
		} else {
			Assert.fail("Could not find element");
		}
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Running test");
		String driverType = "chrome";
		driver = new utilities.DriverFactory().newDriver(driverType);
		driver.get(baseUrl);
		smoketests.Window.testPageTitle(driver, "SDET Training");
	}
	@AfterMethod
	public void afterMethod() {
		smoketests.Window.testPageTitle(driver, "SDET Training");
		System.out.println("Closing test");
		driver.close();
	}
}

