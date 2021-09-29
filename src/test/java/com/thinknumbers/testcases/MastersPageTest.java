package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class MastersPageTest extends TestBase{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	
	public MastersPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		mastersPage = new MastersPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		mastersPage = homePage.goToMasters();
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		Assert.assertEquals(mastersPage.verifyPageTitle(), "MASTERS");		
	}
	
	@Test(priority=2)
	public void verifyMastersMenu() {
		softAssert.assertTrue(mastersPage.verifyCompanyMenu());
		softAssert.assertTrue(mastersPage.verifySubMasterMenu());
		softAssert.assertTrue(mastersPage.verifyAnalysisGroupMenu());
		softAssert.assertTrue(mastersPage.verifyAccountingGroupMenu());
		softAssert.assertTrue(mastersPage.verifyDayBookMenu());
		softAssert.assertTrue(mastersPage.verifyTrailBalancemenu());
		softAssert.assertTrue(mastersPage.verifyFasTrailBalanceMenu());
		softAssert.assertTrue(mastersPage.verifyReportingLedgerMenu());
		softAssert.assertTrue(mastersPage.verifyGettingStartedMenu());
		
		softAssert.assertAll();
	}
	
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	

}
