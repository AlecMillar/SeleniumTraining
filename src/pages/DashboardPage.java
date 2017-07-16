package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

	
	public static String confirmLogin(WebDriver driver) {
		String confirmation = driver.findElement(By.id("MainContent_lblid")).getText();
		return confirmation;
	}
	
	public static void clickLogout(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"MainContent_loggedinlinks\"]/ul/li[4]/a")).click();
		
	}
}
