package pages;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import baseClasses.BaseTestPage;
import baseClasses.BaseTestPageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class SignInPage extends BaseTestPage implements BaseTestPageFactory{
   
	@FindBy(id = "sso_splash_logo")
	private WebElement headerAmazon;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement SignInbtn;

	@FindBy(id = "new_user")
	private WebElement buttonCreateAccount;

	@FindBy(id = "skip_sign_in_button")
	private WebElement skipSingInBtn;
	     
	public void PageLoading() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(SignInbtn));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(TimeoutException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(RuntimeException.class);
	}
	   
	/**
	 *SignIN page
	 */
	public SignInPage verifySignInPage() {
		
		try {
			PageLoading(); 
			Reporter.log("Welcome page");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load SignIn Page");
		}
		return this;
	}
	
	/**
	 * Clicking Sign In
	 */
	public SignInPage clickSignInButton() {
		try {
			SignInbtn.click();
			Reporter.log("Sign in button is clickable");
		} catch (Exception e) {
			Assert.fail("Sign in button is not clickable");
		}
		return this;
	}

	@Override
	public void reInitialze() {
		// TODO Auto-generated method stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
}
