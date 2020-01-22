package baseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author Abinash 
 *
 */
public class BaseMobileClass extends TestExcel {
	public static String udid = "";
	public static String deviceName = "";
	public static String platformName = "";
	public static String platformVersion = "";
	public static String app = "";
	public static String appPackage = "";
	public static String appActivity = "";
	public static String name  = "";
	public static String pwd = "";
	public static String stringSearch = "";
	public static  String desInCart = "";
	public static  String priceIncart ="";
	public static  String priceWhileBuying ="";
	public static String bankDetail = " ";
	public static AndroidDriver driver;
	
	
	public static String getBankDetail() {
		return bankDetail;
	}

	public static void setBankDetail(String bankDetail) {
		BaseMobileClass.bankDetail = bankDetail;
	}



	
	
	public static String getAppPackage() {
		return appPackage;
	}

	public static void setAppPackage(String appPackage) {
		BaseMobileClass.appPackage = appPackage;
	}

	public static String getAppActivity() {
		return appActivity;
	}

	public static void setAppActivity(String appActivity) {
		BaseMobileClass.appActivity = appActivity;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		BaseMobileClass.name = name;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		BaseMobileClass.pwd = pwd;
	}

	public static String getStringSearch() {
		return stringSearch;
	}

	public static void setStringSearch(String stringSearch) {
		BaseMobileClass.stringSearch = stringSearch;
	}
	
	
	
	public static String getUdid() {
		return udid;
	}

	public static void setUdid(String udid) {
		BaseMobileClass.udid = udid;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static void setDeviceName(String deviceName) {
		BaseMobileClass.deviceName = deviceName;
	}

	public static String getPlatformName() {
		return platformName;
	}

	public static void setPlatformName(String platformName) {
		BaseMobileClass.platformName = platformName;
	}

	public static String getPlatformVersion() {
		return platformVersion;
	}

	public static void setPlatformVersion(String platformVersion) {
		BaseMobileClass.platformVersion = platformVersion;
	}
     
	public static String getApp() {
		return app;    
	}

	public static void setApp(String app) {
		BaseMobileClass.app = app;
	}
//GET DATA FROM EXCEL SHEET
	@BeforeSuite      
	public static void startDriver() throws  IOException {
		// obtaining input bytes from a file
		
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook myExcelBook = new XSSFWorkbook(fis);
		XSSFSheet myExcelSheet = myExcelBook.getSheet("MobileDetails");
		XSSFRow row = myExcelSheet.getRow(0);
   
		for (int r = 0; r < row.getLastCellNum(); r++) {
			Row row1 = myExcelSheet.getRow(r); // returns the logical row
			Cell cell = row1.getCell(1);
			String value = cell.getStringCellValue(); // getting cell value
			if (value.equalsIgnoreCase("Y")) {
				System.out.println("The Value for the Yes col is " +r);
				getrowDetails(r);
				break;

			} else {
				continue;     
			}
   
		}    

	}
	// THIS GETS THE ROW VALUE WHERE RUN CONDITION IS YES
	public static void getrowDetails(int valrow) throws IOException {
		try {
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook myExcelBook = new XSSFWorkbook(fis);
		XSSFSheet myExcelSheet = myExcelBook.getSheet("MobileDetails");
		XSSFRow row = myExcelSheet.getRow(valrow);
		System.out.println("The Length of the row is as " + row.getLastCellNum());
		for (int i = 0; i < row.getLastCellNum(); i++) {
			row = myExcelSheet.getRow(valrow); 
			Cell cell = row.getCell(i);   
			// SETTERS TO TAKE VALUE FROM THE EXCEL SHEET 
			setDeviceName(ReadCellData(valrow,2)); 
			setPlatformName(ReadCellData(valrow, 3));
			setUdid(ReadCellData(valrow,4));   
			setPlatformVersion(ReadCellData(valrow,5));
			setApp(ReadCellData(valrow,6));
			setAppPackage(ReadCellData(valrow,7));    
			setAppActivity(ReadCellData(valrow,8));
			setName(ReadCellData(valrow,9));
			setPwd(ReadCellData(valrow,10));
			setStringSearch(ReadCellData(valrow,11));
			setBankName(ReadCellData(valrow,12));
		}
		}
		finally {
			 	if(fis!= null) {
			 			 fis.close();	 	
			 	}
		}
			
			
			
				


	}

// READ FROM PROPERTY FILE
	public static String getPropertyValue(String Key) throws IOException
	{
		Properties OR;
		File f1;
		FileInputStream file;
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")
				+ "\\PropertiesFileAndXpaths\\Object.Properties");
				
		file = new FileInputStream(f1);
		OR.load(file);
		return OR.getProperty(Key);
    
	}

	@BeforeMethod
public static void launchAppilication() throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", getDeviceName());    
		capabilities.setCapability("platformName", getPlatformName()); 
		capabilities.setCapability("udid",getUdid());  
		capabilities.setCapability("platformVersion",getPlatformVersion());
		capabilities.setCapability("app",getApp()); 
		capabilities.setCapability("appPackage", getAppPackage());
		capabilities.setCapability("appActivity", getAppActivity());
		   
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		Reporter.log("App Installed Successfully.");
	}             

 
	public static void swipeVeritcal(AppiumDriver<MobileElement> driver,double startPercentage,double finalPercentage,int duration){
	   
		Dimension size=driver.manage().window().getSize();
		int width = (size.width/2);
		int startPoint= (int)(size.getHeight() * startPercentage);
		int endPoint= (int)(size.getHeight() * finalPercentage);
		
		new TouchAction(driver).press(PointOption.point(width,startPoint)).waitAction().moveTo(PointOption.point(width,endPoint)).release().perform();
	}
	


		

}
