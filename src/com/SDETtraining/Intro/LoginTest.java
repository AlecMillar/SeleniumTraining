package com.SDETtraining.Intro;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {
	// Initialize Static Properties
	WebDriver driver;
	String baseURL = "http://www.sdettraining.com/";
	String email = "tim@testemail.com";
	String password = "trpass";
	int records = email.length();

	@Before
	public void setUp() throws Exception {
		System.out.print("What browser would you like to use? Chrome, Firefox, or IE: ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String browserType = in.next();
		driver = utilities.DriverFactory.newDriver(browserType);
	}

	@Test
	public void AccountLoginTest() {

		driver.get(baseURL + "projects/");
		driver.findElement(By.linkText("Account Management System")).click();
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys(email);
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
		driver.findElement(By.id("MainContent_btnLogin")).click();

		String result = driver.findElement(By.id("MainContent_lblid")).getText();
		if (result.contains("Welcome back!") == true) {
			result = "PASS";
		} else {
			result = "FAIL";
		}
		System.out.println(result);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Closing the browser and sending screenshot to file.");
		utilities.Screenshot.snap(driver, "LoginTest", email);
		driver.findElement(By.xpath(".//*[@id='MainContent_loggedinlinks']/ul/li[4]/a")).click();
		driver.quit();
	}

}
