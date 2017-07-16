package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	//Goal: Create a repository of page elements for each page
	
	// Step 1 - create a class for each page
	
	//Step 2 - create a method for each element
			public static void typeUsername(WebDriver driver, String email) {
				driver.findElement(By.id("MainContent_txtUserName")).sendKeys(email);
			}
			
			public static void TypePassword(WebDriver driver, String password) {
				driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
			}
			public static void clickLogin(WebDriver driver) {
				driver.findElement(By.id("MainContent_btnLogin")).click();
			}
}
