package com.SDETtraining.Intro;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountTest {
	WebDriver driver;
	String baseURL = "http://www.sdettraining.com/";
	String firstName = "Alec";
	String lastName = "Millar";
	String email = "alec@testemail.com";
	String password = "testpassword";
	String phone = "703-975-7323";
	String country = "Australia";
	
	@Test
	public void createNewAccount() {
		/*System.out.print("What browser would you like to use? Chrome, Firefox, or IE: ");
		Scanner in = new Scanner(System.in);
		String browserType = in.next();*/
		String browserType = "firefox";

		//Instantiate driver
		driver = utilities.DriverFactory.newDriver(browserType);
		driver.get(baseURL + "projects/");
		driver.findElement(By.linkText("Account Management System")).click();
		driver.findElement(By.linkText("Create Account")).click();
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
	}
	
	@After
	public void tearDown() {
		System.out.println("Closing the browser, writing results to file, generally ending test.");
		utilities.Screenshot.snap(driver, "CreateAccountTest", email);
		driver.quit();
	}

}
