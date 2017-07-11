package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	
	// This method will return a WebDriver
	// Default browser is Chrome
	public static WebDriver newDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\JARs\\chromedriver.exe");
		return new ChromeDriver();
	}
	// User determines browser through overloading by passing browser type
	public static WebDriver newDriver(String browserType) {
		if (browserType.equalsIgnoreCase("firefox")) {
			System.out.println("Using Firefox");
			System.setProperty("webdriver.gecko.driver", "C:\\JARs\\geckodriver.exe");
			return new FirefoxDriver();
		}
		else if (browserType.equalsIgnoreCase("chrome")) {
			System.out.println("Using Chrome");
			System.setProperty("webdriver.chrome.driver", "C:\\JARs\\chromedriver.exe");
			return new ChromeDriver();
		}
		else {
			System.out.println("Using Internet Explorer");
			System.setProperty("webdriver.ie.driver", "C:\\JARs\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
	}
	
}
