package pages;

import java.util.List;
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


public class SearchResultPage extends BaseTestPage implements BaseTestPageFactory{
       
	
	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/item_title\"]")
    private List<WebElement> resultItem;

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_corrections_mixed_quartz']")
	private WebElement labelSearchResults;

	
	public void PageLoading() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(labelSearchResults));
	}	   
	
	/**
	 * Verify Search Result page loaded
	 */
	public SearchResultPage verifySearchResultPageDisplayed() {
		
		try {
			PageLoading();
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Search Result page");
		}
		return this;
	}
		
	/**
	 * Get item name from search results page
	 * @return
	 */
	
	public String getItemName() {
		String deviceName = "";
		try {
			deviceName = resultItem.get(2).getText();;
			Reporter.log("Got item name from search results page");
		} catch (Exception e) {
			Assert.fail("Failed to get item name from search results page");
		}
		return deviceName;   
	}
	@Override
	public void reInitialze() {
		// TODO Auto-generated method stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
}
