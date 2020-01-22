package TestCases;

import org.testng.annotations.Test;

import baseClasses.BaseTestPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchResultPage;
import pages.SignInPage;


public class TestCase001 extends BaseTestPage {

	
	@Test
	public void testSearchAndCompare() throws Exception {
		
		SignInPage objSigIn= new SignInPage();
		objSigIn.verifySignInPage();   
		objSigIn.clickSignInButton();         
		     
		LoginPage Objlogin = new LoginPage();
		Objlogin.verifyLogInPage();
		Objlogin.userLogIn(); //Calling login method

		HomePage ObjhomePage = new HomePage();
		ObjhomePage.verifyHomePageDisplayed();
		ObjhomePage.EnterKeywordAndSearchItem(); //Method to enter search keyword and search
		
		SearchResultPage ObjSearch = new SearchResultPage();
		ObjSearch.verifySearchResultPageDisplayed();
		String expectedItemName = ObjSearch.getItemName();

		ProductDetailsPage objProductDetailPage = new ProductDetailsPage();
		objProductDetailPage.verifyProductDetailsPageDisplayed();
		objProductDetailPage.clickBuyNowButton();
		
		CheckoutPage ObjCheckOut = new CheckoutPage();
		ObjCheckOut.verifyCheckOutPaymentsPageDisplayed();
		ObjCheckOut.clickNetBankingRadioButton();
		ObjCheckOut.selectBankName();
		ObjCheckOut.clickContinueButton(); 
		
		String actualItemName = ObjCheckOut.getItemNameOnCheckOut();
		ObjCheckOut.compareItemNames(actualItemName, expectedItemName);	//Comparing the item name from product search and checkout page
	}
}
