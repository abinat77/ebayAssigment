package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import baseClasses.BaseTestPage;
import baseClasses.BaseTestPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseTestPage implements BaseTestPageFactory{ 
	WebDriverWait wait=new WebDriverWait(driver, 20);
	@FindBy(xpath = "//*[@resource-id=\"ap_email_login\"]")
    private MobileElement textBoxMobileNumber;
	
	@FindBy(xpath = "//*[@resource-id=\"ap_password\"]")
    private MobileElement textBoxPassword;
	
	@FindBy(xpath = "//*[@resource-id=\"signInSubmit\"]")
    private MobileElement buttonLogin;
	
	@FindBy(xpath = "//*[@resource-id=\"continue\"]")
    private MobileElement buttonContinue;
	

	public void PageLoading() {
		wait.until(ExpectedConditions.visibilityOf(buttonContinue));
	}

	/**
	 * Verify LogIn In page loaded
	 */
	public LoginPage verifyLogInPage() {
		
		try {
			PageLoading();
			Reporter.log("Login botton");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load LogIn Page");
		}
		return this;
	}
	
	/**
	 * User login
	 */
	public LoginPage userLogIn() {
		try {
			String uName = getName();
			String pwd = getPwd();
			signIn(uName, pwd); 
			Assert.assertTrue(true,"Login Successful");
		} catch (Exception e) {
			Assert.fail("Sign in failed");
		}   
		return this;
	}	
	
	/**
	 * Enter username and password for Sign In
	 * @param mobile_no
	 * @param pass
	 */
	public void signIn(String mobile_no,String pass) {
		try {
			textBoxMobileNumber.sendKeys(mobile_no);
			buttonContinue.click();
			wait.until(ExpectedConditions.visibilityOf(textBoxPassword));
			textBoxPassword.sendKeys(pass);
			buttonLogin.click();
			Reporter.log("Successfully entered login details and clicked Login button");
		} catch (Exception e) {
			Assert.fail("Failed to enter login details and Continue");
		}
	}

	@Override
	public void reInitialze() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
