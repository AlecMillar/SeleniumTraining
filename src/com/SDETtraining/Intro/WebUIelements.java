package com.SDETtraining.Intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebUIelements {

	public static void main(String[] args) {
		/* Test plan
		 * Write a test plan to test the user sign up application
		 */
		
		// Properties
		String baseURL = "http://www.sdettraining.com/";
		/*System.out.print("What browser would you like to use? Chrome, Firefox, or IE: ");
		Scanner in = new Scanner(System.in);
		String browserType = in.next();*/
		//String browserType = "firefox";
		
		//Instantiate driver
		System.setProperty("webdriver.gecko.driver", "C:\\JARs\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = DriverFactory.newDriver(browserType);
		driver.get(baseURL + "projects/");
		driver.findElement(By.linkText("Account Management System")).click();
		driver.findElement(By.linkText("Create Account")).click();
		driver.findElement(By.id("MainContent_txtFirstName")).sendKeys("Alec");
		driver.findElement(By.id("MainContent_txtLastName")).sendKeys("Millar");
		driver.findElement(By.id("MainContent_Male")).click();
		driver.findElement(By.id("MainContent_txtEmail")).sendKeys("alec@testemail.com");
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("testpassword");
		driver.findElement(By.id("MainContent_txtVerifyPassword")).sendKeys("testpassword");
		//driver.findElement(By.id("MainContent_txtHomePhone")).sendKeys("703-975-7323");
		//driver.findElement(By.id("MainContent_txtCellPhone")).sendKeys("703-975-7323");
		//Using xPath for example for phone #s
		driver.findElement(By.xpath(".//*[@id='MainContent_txtHomePhone']")).sendKeys("703-975-7323");
		driver.findElement(By.xpath(".//*[@id='MainContent_txtCellPhone']")).sendKeys("703-975-7323");
		// For a drop down, we need to create a new object of type Select
		new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByValue("Australia");
		
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

		// XPath: identify elements based upon the location in the DOM (Document Object Model: html document)
		//driver.findElement(By.xpath(".//*[@id='HeadLoginView_lblLoginStatus']")).click();
	}

}
