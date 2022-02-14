package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.RLReportingLedgerListsPage;
import com.thinknumbers.pages.ReportRunPublishReportPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class ReportRunPublishReportPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	RLReportingLedgerListsPage rLReportingLedgerListsPage;
	ReportRunPublishReportPage reportRunPublishReportPage;
	
	String sheetName = "PublishReport";
	
	public ReportRunPublishReportPageTest() {
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
		TestUtil.WaitTill_PageLoads(100);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		reportRunPublishReportPage = masterPage.goToReportRunPublishReportPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, enabled=true, dataProvider="getTestData")
	public void publishReportTest(String inputTemplate, String column, String fromDate, String toDate, String colTotal, 
			String decimalOption, String figConversion, String reportName, String userGroups, String users) throws InterruptedException {

		reportRunPublishReportPage.publishReport(inputTemplate, column, fromDate, toDate, colTotal, decimalOption, figConversion, reportName, userGroups, users);
		
		//Thread.sleep(5000);
		Assert.assertEquals(reportRunPublishReportPage.readAlertMessage(), "Report Published Successfully..");
	}
	
	@Test(priority=2, enabled=true, dataProvider="getTestData")
	public void duplicatePublishReportTest(String inputTemplate, String column, String fromDate, String toDate, String colTotal, 
			String decimalOption, String figConversion, String reportName, String userGroups, String users) {

		reportRunPublishReportPage.publishReport(inputTemplate, column, fromDate, toDate, colTotal, decimalOption, figConversion, reportName, userGroups, users);
		
		//Thread.sleep(5000);
		Assert.assertEquals(reportRunPublishReportPage.readAlertMessage(), "Report Name Already Exists Or Not Available");
	}
	
	@Test(priority=3, enabled=true)
	public void updatePublishReportTest() {
		reportRunPublishReportPage.updatePublishReport();
		//Thread.sleep(2000);
		Assert.assertEquals(reportRunPublishReportPage.readAlertMessage(), "Report Updated Successfully..");
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
