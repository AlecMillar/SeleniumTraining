package com.SDETtraining.ams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.DriverFactory;

public class AccountManagementDDT {

	WebDriver driver;
	String baseUrl = "http://sdettraining.com/trguitransactions/Account.aspx";

	@Test (priority=1, dataProvider = "getNewData")
	public void NewUserTest(String firstName, String lastName, String email, String password, String phone, String country) {

		// Navigates to Create Account page and fills out account info
		driver.findElement(By.id("createaccount")).click();
		driver.findElement(By.id("MainContent_txtFirstName")).sendKeys(firstName);
		driver.findElement(By.id("MainContent_txtLastName")).sendKeys(lastName);
		driver.findElement(By.id("MainContent_Male")).click();
		driver.findElement(By.id("MainContent_txtEmail")).sendKeys(email);
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
		driver.findElement(By.id("MainContent_txtVerifyPassword")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='MainContent_txtHomePhone']")).sendKeys(phone);
		driver.findElement(By.xpath(".//*[@id='MainContent_txtCellPhone']")).sendKeys(phone);
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByValue(country);

		// Ensures that emails are checked and updates is unchecked.
		if (!(driver.findElement(By.id("MainContent_checkWeeklyEmail")).isSelected())){
			System.out.println("Checking Weekly Emails.");
			driver.findElement(By.id("MainContent_checkWeeklyEmail")).click();
		}
		if (!(driver.findElement(By.id("MainContent_checkMonthlyEmail")).isSelected())){
			driver.findElement(By.id("MainContent_checkMonthlyEmail")).click();
			System.out.println("Checking Monthly Emails.");
		}
		if ((driver.findElement(By.id("MainContent_checkUpdates")).isSelected())){
			driver.findElement(By.id("MainContent_checkUpdates")).click();
			System.out.println("Unchecking Updates.");
		}

		// Submits account info
		driver.findElement(By.id("MainContent_txtInstructions")).sendKeys("Automatically filled by Selenium for testing purposes.");
		driver.findElement(By.id("MainContent_btnSubmit")).click();

		//	Assert response/confirmation
		String confirmation = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		String actual = "Customer information added successfully";
		Assert.assertEquals(confirmation,actual);
	}

	@Test (priority=2, dataProvider = "getLoginData")
	public void LoginTest(String email, String password) {
		
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
		new utilities.DriverFactory();
		driver = DriverFactory.newDriver("chrome");
		driver.get(baseUrl);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public String[][] getNewData() {
		return datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/newdata.xls");
	}
	@DataProvider
	public String[][] getLoginData() {
		return datadriven.Excel.get("C:/Users/Alec/Google Drive/Work/SDET Training/Test Data/logindata.xls");
	}

}
