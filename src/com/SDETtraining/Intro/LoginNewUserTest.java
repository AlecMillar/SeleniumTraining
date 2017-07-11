package com.SDETtraining.Intro;

import java.util.Scanner;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/*
 / Code looks great, very clean and well organized.
 / Probably should add comments at each block/section to tell others (like me) what you are doing.
 / You are using JUnit framework for your tests. Try using Assert methods to validate your tests.
*/

public class LoginNewUserTest {
	// Initialize Static Properties
	WebDriver driver;
	//emails: tim@testemail.com, rk@testemail.com, sm@testemail.com
	//passwords: trpass, rkpass, smpass
	String[] firstNames = {"Alec", "Scott", "Jacob"};
	String[] lastNames = {"Millar", "Hale", "Marron"};
	String[] emails = {"tim@testemail.com", "rk@testemail.com", "sm@testemail.com"};
	String[] passwords = {"trpass", "rkpass", "smpass"};
	String[] phones = {"123-456-7890", "987-654-3210", "434-334-1423"};
	String[] countries = {"Australia", "China", "Denmark"};
	int records = emails.length;
	String baseURL = "http://sdettraining.com/trguitransactions/Account.aspx";

	@Test
	public void testLogin() {
		for (int i=0; i<records; i++) {
			System.out.print("What browser would you like to use? Chrome, Firefox, or IE: ");
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String browserType = in.next();
			driver = utilities.DriverFactory.newDriver(browserType);

			driver.get(baseURL);
			driver.findElement(By.id("MainContent_txtUserName")).sendKeys(emails[i]);
			driver.findElement(By.id("MainContent_txtPassword")).sendKeys(passwords[i]);
			driver.findElement(By.id("MainContent_btnLogin")).click();

			String result = driver.findElement(By.id("MainContent_lblid")).getText();
			if (result.contains("Welcome back!") == true) {
				result = "PASS";
			} else {
				result = "FAIL";
			}
			System.out.println(result);
			System.out.println("Closing the browser and sending screenshot to file.");
			String filename = "LoginTest" + Integer.toString(i+1);
			utilities.Screenshot.snap(driver, filename, emails[i]);
			driver.findElement(By.xpath(".//*[@id='MainContent_loggedinlinks']/ul/li[4]/a")).click();
			driver.quit();
		}
	}

	@Test
	public void testNewUser() {
		for (int i=0; i<records; i++) {
			System.out.print("What browser would you like to use? Chrome, Firefox, or IE: ");
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String browserType = in.next();

			driver = utilities.DriverFactory.newDriver(browserType);
			driver.get(baseURL);
			driver.findElement(By.id("createaccount")).click();
			driver.findElement(By.id("MainContent_txtFirstName")).sendKeys(firstNames[i]);
			driver.findElement(By.id("MainContent_txtLastName")).sendKeys(lastNames[i]);
			driver.findElement(By.id("MainContent_Male")).click();
			driver.findElement(By.id("MainContent_txtEmail")).sendKeys(emails[i]);
			driver.findElement(By.id("MainContent_txtPassword")).sendKeys(passwords[i]);
			driver.findElement(By.id("MainContent_txtVerifyPassword")).sendKeys(passwords[i]);
			driver.findElement(By.xpath(".//*[@id='MainContent_txtHomePhone']")).sendKeys(phones[i]);
			driver.findElement(By.xpath(".//*[@id='MainContent_txtCellPhone']")).sendKeys(phones[i]);
			new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByValue(countries[i]);

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
			System.out.println("Closing browser & ending test.");
			driver.quit();
		}
	}

}
