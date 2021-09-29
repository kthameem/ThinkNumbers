package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.SubMasterCurrencyPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class SubMasterCurrencyPageTest extends TestBase{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	SubMasterCurrencyPage subMasterCurrencyPage;
	
	String sheetName = "SubMasterCurrency";
	
	public SubMasterCurrencyPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		mastersPage = new MastersPage();
		subMasterCurrencyPage = new SubMasterCurrencyPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		mastersPage = homePage.goToMasters();
		homePage.clearAlertMessage();
		subMasterCurrencyPage = mastersPage.goToSubMasterPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData")
	public void addCurrencyTest(String code, String name) throws InterruptedException {
		subMasterCurrencyPage.addCurrency(code, name);
		Thread.sleep(500);
		
		softAssert.assertEquals(subMasterCurrencyPage.readAlertMessage(), "Currency Added Successfully");
		if (subMasterCurrencyPage.readAlertMessage()!= "Currency Added Successfully") {
			subMasterCurrencyPage.closeCurrency();
		}
		softAssert.assertAll();
		subMasterCurrencyPage.clearAlertMessage();
	}
	
	@Test(priority=2, dataProvider="getTestData")
	public void addDuplicateCurrencyTest(String code, String name) throws InterruptedException {
		subMasterCurrencyPage.addCurrency(code, name);
		Thread.sleep(500);
		
		if (subMasterCurrencyPage.readAlertMessage()== "Entered Code Already Available") {
			softAssert.assertEquals(subMasterCurrencyPage.readAlertMessage(), "Entered Code Already Available");
		} else if (subMasterCurrencyPage.readAlertMessage()== "Entered Currency Name Already Available") {
			softAssert.assertEquals(subMasterCurrencyPage.readAlertMessage(), "Entered Currency Name Already Available");
		}
		subMasterCurrencyPage.closeCurrency();
		
		softAssert.assertAll();
		subMasterCurrencyPage.clearAlertMessage();
	}
	
	@Test(priority=3)
	public void searchCurrencyTest() {
		Assert.assertEquals(subMasterCurrencyPage.searchCurrency("INR"), "INR");
	}
	
	@Test(priority=4)
	public void editCurrencyTest() {
		subMasterCurrencyPage.editCurrency("EURO", "EURO", "EURO");
		softAssert.assertEquals(subMasterCurrencyPage.readAlertMessage(), "Currency Updated Successfully");
		softAssert.assertAll();
		subMasterCurrencyPage.clearAlertMessage();
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
