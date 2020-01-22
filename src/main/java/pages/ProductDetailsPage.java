package pages;

import org.openqa.selenium.NoSuchElementException;
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


public class ProductDetailsPage extends BaseTestPage implements BaseTestPageFactory{


	@FindBy(xpath = "//*[@resource-id='titleExpander']")
	private WebElement labelProductTitle;
	
	@FindBy(xpath = "//*[@resource-id='buyNowCheckout']")
	private WebElement buttonBuyNow;

	
	public void PageLoading() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(labelProductTitle));
	}	
	
	/**
	 * Verify Product Details page loaded
	 */
	public ProductDetailsPage verifyProductDetailsPageDisplayed() {
		
		try {
			PageLoading();
			Reporter.log("In Product detail page");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Product Details page");
		}
		return this;
	}
	
	/**
	 * Buy Now button click on Product details page
	 * @return
	 */
	
	public ProductDetailsPage clickBuyNowButton() {
		try {
			while(!(buttonBuyNow.isDisplayed())){ 	
			swipeVeritcal(driver, 0.9, 0.2, 2); //Scroll Down till Buy Now button displayed
			}
			buttonBuyNow.click();
			Reporter.log("Buy Now button is clicked");
		} catch (Exception e) {
			Assert.fail("Failed to click Buy Now button");
		}
		return this;
	}

	@Override
	public void reInitialze() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
}
