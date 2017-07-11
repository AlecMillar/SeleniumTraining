package com.SDETtraining.ams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewUser {
	
	WebDriver driver;

	@Test(dataProvider = "dp")
	public void testNewUser(String firstName, String lastName, String email, String password, String phone, String country) {
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
		driver.findElement(By.id("MainContent_txtInstructions")).sendKeys("Automatically filled by Selenium for testing purposes.");
		driver.findElement(By.id("MainContent_btnSubmit")).click();
	
		//	Assert response/confirmation
		String confirmation = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
		String actual = "Customer information added successfully";
		Assert.assertEquals(confirmation,actual);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new utilities.DriverFactory().newDriver("chrome");
		driver.get("http://sdettraining.com/trguitransactions/Account.aspx");
	}
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}


	@DataProvider
	public Object[][] dp() {
		return new Object[][] {
			new Object[] { "Don", "Wilson", "dwil@testemail.com", "dwipass", "1234567890", "Albania" },
			new Object[] { "Rachel", "Johnson", "rjhon@testemail.com", "rjhpass", "0987654321", "Australia" },
			new Object[] { "Alex", "Larry", "alar@testemail.com", "alapass", "1231231234", "China" },
		};
	}
}
