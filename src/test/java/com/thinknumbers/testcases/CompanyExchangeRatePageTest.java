package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.CompanyExchangeRatePage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class CompanyExchangeRatePageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	CompanyExchangeRatePage companyExchangeRatePage;
	
	String sheetName = "ExchangeRate";
	
	public CompanyExchangeRatePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		companyExchangeRatePage = new CompanyExchangeRatePage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(100);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		companyExchangeRatePage = masterPage.goToCompanyExchangeRatePage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData")
	public void addExchangeRateTest(String year, String month, String currency, String toCurrency, String value) {
		companyExchangeRatePage.addExchangeRate();
		companyExchangeRatePage.enterExchangeRate(year, month, currency, toCurrency, value);
		companyExchangeRatePage.saveExchangeRate();
		
		Assert.assertEquals(companyExchangeRatePage.readAlertMessage(), "Exchange Rate Added Successfully");
	}
	
	@Test(priority=2, dataProvider="getTestData")
	public void duplicateExchangeRateTest(String year, String month, String currency, String toCurrency, String value) {
		companyExchangeRatePage.addExchangeRate();
		companyExchangeRatePage.enterExchangeRate(year, month, currency, toCurrency, value);
		companyExchangeRatePage.saveExchangeRate();	
		companyExchangeRatePage.closeExchangeRate();
		
		Assert.assertEquals(companyExchangeRatePage.readAlertMessage(), "Exchange Rate Already Exists");
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
