package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.RLReportingLedgersToAccLedgersMappingPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class RLReportingLedgersToAccLedgersMappingPageTest extends BaseTest {
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	RLReportingLedgersToAccLedgersMappingPage rLReportingLedgersToAccLedgersMappingPage;
	
	String sheetName = "ReportingLedger";
	
	public RLReportingLedgersToAccLedgersMappingPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		rLReportingLedgersToAccLedgersMappingPage = new RLReportingLedgersToAccLedgersMappingPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		rLReportingLedgersToAccLedgersMappingPage = masterPage.goToRLReportingLedgersToAccLedgersMappingPage();
	}
	
	@Test(priority=1)
	public void mapReportingLedgerRevenueTest() throws InterruptedException {
		Thread.sleep(3000);
		rLReportingLedgersToAccLedgersMappingPage.mapRL();
		Assert.assertEquals(rLReportingLedgersToAccLedgersMappingPage.readAlertMessage(), "Reporting Ledger Component Updated Successfully");
		
	}
	
	@Test(priority=2)
	public void mapReportingLedgerExpenseTest() throws InterruptedException {
		Thread.sleep(3000);
		rLReportingLedgersToAccLedgersMappingPage.goToExpenseTab();
		Thread.sleep(5000);
		rLReportingLedgersToAccLedgersMappingPage.mapRL();
		Assert.assertEquals(rLReportingLedgersToAccLedgersMappingPage.readAlertMessage(), "Reporting Ledger Component Updated Successfully");
	}
	
	@Test(priority=3)
	public void mapReportingLedgerLiabilityTest() throws InterruptedException {
		Thread.sleep(3000);
		rLReportingLedgersToAccLedgersMappingPage.goToLiabilityTab();
		Thread.sleep(5000);
		rLReportingLedgersToAccLedgersMappingPage.mapRL();
		Assert.assertEquals(rLReportingLedgersToAccLedgersMappingPage.readAlertMessage(), "Reporting Ledger Component Updated Successfully");
	}
	
	@Test(priority=4)
	public void mapReportingLedgerAssetTest() throws InterruptedException {
		Thread.sleep(3000);
		rLReportingLedgersToAccLedgersMappingPage.goToAssetTab();
		Thread.sleep(3000);
		rLReportingLedgersToAccLedgersMappingPage.mapRL();
		Assert.assertEquals(rLReportingLedgersToAccLedgersMappingPage.readAlertMessage(), "Reporting Ledger Component Updated Successfully");
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
