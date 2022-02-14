package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.CompanyCurrencyPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class CompanyCurrencyPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	CompanyCurrencyPage companyCurrencyPage;
	
	String sheetName = "Currency";
	
	public CompanyCurrencyPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		companyCurrencyPage = new CompanyCurrencyPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		companyCurrencyPage = masterPage.goToCompanyCurrencyPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addCurrencyTest(String code, String name) throws InterruptedException {
		companyCurrencyPage.addCurrency(code, name);
		Thread.sleep(1000);
		
		softAssert.assertEquals(companyCurrencyPage.readAlertMessage(), "Currency Added Successfully");
		if (companyCurrencyPage.readAlertMessage().contains("Currency Added Successfully")) {
			companyCurrencyPage.clearAlertMessage();	
		} else {
			companyCurrencyPage.closeCurrency();
		}
		softAssert.assertAll();
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void addDuplicateCurrencyTest(String code, String name) throws InterruptedException {
		companyCurrencyPage.addCurrency(code, name);
		Thread.sleep(500);
		
		if (companyCurrencyPage.readAlertMessage()== "Entered Code Already Available") {
			softAssert.assertEquals(companyCurrencyPage.readAlertMessage(), "Entered Code Already Available");
		} else if (companyCurrencyPage.readAlertMessage()== "Entered Currency Name Already Available") {
			softAssert.assertEquals(companyCurrencyPage.readAlertMessage(), "Entered Currency Name Already Available");
		}
		companyCurrencyPage.closeCurrency();
		
		softAssert.assertAll();
		companyCurrencyPage.clearAlertMessage();
	}
	
	@Test(priority=3, enabled=true)
	public void searchCurrencyTest() {
		Assert.assertEquals(companyCurrencyPage.searchCurrency("INR"), "INR");
	}
	
	@Test(priority=4, enabled=true)
	public void editCurrencyTest() throws InterruptedException {
		companyCurrencyPage.editCurrency("INR", "INR", "Rupees");
		Thread.sleep(500);
		softAssert.assertEquals(companyCurrencyPage.readAlertMessage(), "Currency Updated Successfully");
		softAssert.assertAll();
		companyCurrencyPage.clearAlertMessage();
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
