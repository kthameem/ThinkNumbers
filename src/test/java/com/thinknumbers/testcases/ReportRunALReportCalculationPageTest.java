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
import com.thinknumbers.pages.RLReportingLedgerListsPage;
import com.thinknumbers.pages.ReportRunALReportCalculationPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class ReportRunALReportCalculationPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	RLReportingLedgerListsPage rLReportingLedgerListsPage;
	ReportRunALReportCalculationPage reportRunALReportCalculationPage;
	
	public ReportRunALReportCalculationPageTest() {
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
		reportRunALReportCalculationPage = masterPage.goToReportRunALReportCalculationPage();
	}
	
	@Test(priority=1, enabled=true)
	public void runMonthlyTest() throws InterruptedException {
		reportRunALReportCalculationPage.runMonthly(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1));
		Thread.sleep(2000);
		Assert.assertEquals(reportRunALReportCalculationPage.readAlertMessage(), "Added Successfully");
	}
	
	@Test(priority=2, enabled=true)
	public void runQuaterlyTest() throws InterruptedException {
		reportRunALReportCalculationPage.runQuaterly(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1));
		Thread.sleep(2000);
		Assert.assertEquals(reportRunALReportCalculationPage.readAlertMessage(), "Added Successfully");
	}
	
	@Test(priority=3, enabled=true)
	public void runDailyTest() throws InterruptedException {
		reportRunALReportCalculationPage.runDaily(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1));
		Thread.sleep(2000);
		Assert.assertEquals(reportRunALReportCalculationPage.readAlertMessage(), "Added Successfully");
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
