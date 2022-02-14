package com.thinknumbers.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.RLReportingLedgerListsPage;
import com.thinknumbers.pages.ReportRunReportCalculatedPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class ReportRunReportCalculatedPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	RLReportingLedgerListsPage rLReportingLedgerListsPage;
	ReportRunReportCalculatedPage reportRunReportCalculatedPage;
	
	public ReportRunReportCalculatedPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		rLReportingLedgerListsPage = masterPage.goToRLReportingLedgerListsPage();
	}
	
	@Test(priority=1, enabled=true)
	public void viewReportCalculatedTest() throws InterruptedException {
		String rlName[] = rLReportingLedgerListsPage.getRepLedName();

		Thread.sleep(2000);
		reportRunReportCalculatedPage = masterPage.goToReportRunReportCalculatedPage();
		Thread.sleep(5000);
		reportRunReportCalculatedPage.viewReportCalculated(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), rlName);
		Thread.sleep(5000);
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
