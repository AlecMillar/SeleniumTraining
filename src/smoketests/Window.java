package smoketests;


import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class Window {
	
	// This method tests the page title
	public static void testPageTitle(WebDriver driver, String expected) {
		String actual = driver.getTitle();
		System.out.println("PAGE TITLE: " + actual);
		if (!actual.contains(expected)) {
			Assert.fail("PAGE TITLE TEST FAILED");
		}
	}

}
