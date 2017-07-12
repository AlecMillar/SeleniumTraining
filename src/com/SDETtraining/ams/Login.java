package com.SDETtraining.ams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.DriverFactory;

public class Login {
	// Initialization of static parameters
	WebDriver driver;
	String baseUrl = "http://sdettraining.com/trguitransactions/Account.aspx";

	@Test(dataProvider = "getLoginData")
	public void testLogin(String email, String password) {
		
		// Enters login data and attempts to login
		driver.findElement(By.id("MainContent_txtUserName")).clear();
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(email);
		driver.findElement(By.id("MainContent_txtPassword")).clear();
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
		driver.findElement(By.id("MainContent_btnLogin")).click();

		// Checks for successful login
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
		new utilities.DriverFactory();
		driver = DriverFactory.newDriver(driverType);
		driver.get(baseUrl);
		smoketests.Window.testPageTitle(driver, "SDET Training");
		
	}
	@AfterMethod
	public void afterMethod() {
		smoketests.Window.testPageTitle(driver, "SDET Training");
		System.out.println("Closing test");
		driver.quit();
	}
	
	@DataProvider
	public String[][] getLoginData() {
		return datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/logindata.xls");
	}
}

