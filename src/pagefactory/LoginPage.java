package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// Define all web elements
	WebDriver driver;
	
	@FindBy(id="MainContent_txtUserName")
	WebElement usernameText;
	@FindBy(id="MainContent_txtPassword")
	WebElement passwordText;
	@FindBy(id="MainContent_btnLogin")
	WebElement loginButton;
	
	
	// By using PageFactory
	// We will create objects for each page
	// When instantiated, that Page Object will initialize all our elements
	// Our methods are then the actual complete actions
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Define Action Methods
	public void loginToAccount(String email, String password) {
		setUsername(email);
		setPassword(password);
		clickLogin();
		
	}
	
	private void setUsername(String email) {
		usernameText.sendKeys(email);
	}
	
	private void setPassword(String password) {
		passwordText.sendKeys(password);
	}
	
	private void clickLogin() {
		loginButton.click();
	}
	
}
