package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	// This method takes a screenshot of the browser and saves as a file
	public static void snap(WebDriver driver, String filename, String suffix) {
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // Creates a file from the screenshot of the driver instance
		try {
			FileUtils.copyFile(screenshotFile, new File ("C:/Users/Alec/Google Drive/Work/TestScreenshots/" + filename + "_" + suffix + ".jpg"));
		} catch (IOException e) {
			System.out.println("Could not save file");
			e.printStackTrace();
		}
	}

}
