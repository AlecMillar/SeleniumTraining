package com.SDETtraining.Intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Introduction {
	// Example Testing script using SDETtraining.com
	
	public static void main(String[] args){
		// START: Generate the test outline
		
		// Step 1. Configure the WebDriver object
			// 1a. Declare the driver
		WebDriver driver;
			// 1b. Set the driver properties
		System.setProperty("webdriver.gecko.driver", "C:\\JARs\\geckodriver.exe");
			// 1c. Instantiate the driver
		driver = new FirefoxDriver();
			// 1d. Open the browser
		driver.get("https://www.google.com/");
		
		// Step 2. Navigated to the Projects Page >> http://www.sdettraining.com/projects/
		driver.navigate().to("http://www.sdettraining.com/projects/");
		
		// Step 3. Clicked on the Account Management Link
			// 3a. Locate the element (Locate by... linkText, ID, class, xPath, etc.)
			// 3b. Perform the action (clicking in this case)
		driver.findElement(By.linkText("Account Management System")).click();
		// Step 4. Fill in test fields (in this case, login info
			// 4a. Enter email address into email text box
				// Determine the by.method >> ID in this case
				// Perform the action >> sendKeys in this case
		driver.findElement(By.id("MainContent_txtUserName")).sendKeys("tim@testemail.com");
			// 4b. Enter password into password text box
		driver.findElement(By.id("MainContent_txtPassword")).sendKeys("trpass");
		
		// Step 5. Click login
		driver.findElement(By.id("MainContent_btnLogin")).click();
		
		// Step 6. We should get confirmation of successful login
		String result = driver.findElement(By.id("MainContent_lblid")).getText();
		if (result.contains("Welcome back!") == true) {
			result = "PASS";
		} else {
			result = "FAIL";
		}
		System.out.println(result);
		
		driver.close();
		//driver.quit() >> Closes all instances of the safe mode browser

	}

}
