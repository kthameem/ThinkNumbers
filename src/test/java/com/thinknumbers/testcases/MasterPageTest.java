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
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class MasterPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	
	public MasterPageTest() {
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
	}
	
	@Test(priority=1, enabled=true)
	public void verifyPageTitleTest() {
		Assert.assertEquals(masterPage.verifyPageTitle(), "MASTER");		
	}
	
	@Test(priority=2, enabled=true)
	public void verifyMastersMenuTest() throws InterruptedException {
		Thread.sleep(1000);
		softAssert.assertTrue(masterPage.verifyGettingStartedMenu());
		softAssert.assertTrue(masterPage.verifyCompanyMenu());
		softAssert.assertTrue(masterPage.verifyFasMastersMenu());
		softAssert.assertTrue(masterPage.verifyOperatingMetricsMenu());
		softAssert.assertTrue(masterPage.verifyUploadsMenu());
		softAssert.assertTrue(masterPage.verifyUploadListMenu());
		softAssert.assertTrue(masterPage.verifyReportingLedgersMenu());
		softAssert.assertTrue(masterPage.verifyReportDesignMenu());
		softAssert.assertTrue(masterPage.verifyDashboardDesignMenu());
		softAssert.assertTrue(masterPage.verifyReportRunMenu());
		softAssert.assertTrue(masterPage.verifyUserDetailsMenu());
		
		softAssert.assertAll();
	}
	
	@Test(priority=3, enabled=true)
	public void verifyMastersPages() throws InterruptedException {
		masterPage.goToCompanyCurrencyPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyCompanyExchangeRate(), "Company Exchange Rate Failed");
		softAssert.assertTrue(masterPage.verifyCompanyGroupEntityDetail(), "Company Group Entity Details Failed");
		masterPage.goToDashboardDesignKeyNumbersPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyDashboardDesignCharts(), "Dashboard Design Charts Failed");
		softAssert.assertTrue(masterPage.verifyDashboardDesignColorSetup(), "Dashboard Design Color Setup Failed");
		softAssert.assertTrue(masterPage.verifyDashboardDesignDashboardCreation(), "Dashboard Design Dashboard Creation Failed");
		softAssert.assertTrue(masterPage.verifyDashboardDesignTables(), "Dasboard Desgin Tables Failed");
		masterPage.goToFasMastersFasGroupListPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyFasMastersAnalysisGroup(), "Fas Masters Analysis Group Failed");
		softAssert.assertTrue(masterPage.verifyFasMastersAnalysisGroupDetails(), "Fas Masters Analysis Group Detials Failed");
		softAssert.assertTrue(masterPage.verifyFasMastersCostCategory(), "Fas Masters Cost Category Failed");
		softAssert.assertTrue(masterPage.verifyFasMastersCostCenter(), "Fas Masters Const Center Failed");
		masterPage.scrollRight();
		Thread.sleep(2000);
		softAssert.assertTrue(masterPage.verifyFasMastersEmployee(), "Fas Masters Employee Failed");
		//Assert.assertTrue(masterPage.verifyFasMastersLedgerList());
		softAssert.assertTrue(masterPage.verifyFasMastersMapWithLedger(), "Fas Masters Map With Ledger Failed");
		softAssert.assertTrue(masterPage.verifyFasMastersVoucherType(), "Fas Master Voucher Type Failed");
		masterPage.goToOMLedgerTypePage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyOMOperatingMetrics(), "OM Operationsal Metrics Failed");
		masterPage.goToReportDesignReportSectionPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyReportDesignReportTemplate(), "Report Design Report Template Failed");
		masterPage.goToReportRunDBRLMapPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyReportRunALReportCalculation(), "Report RunAL Report Calculation Failed");
		softAssert.assertTrue(masterPage.verifyReportRunPublishReport(), "Report Run Publish Report Failed");
		softAssert.assertTrue(masterPage.verifyReportRunReportCalculated(), "Report Run Report Calculaed Failed");
		softAssert.assertTrue(masterPage.verifyReportRunReportCalculation(), "Report Run Report Calculation Failed");
		masterPage.goToRLReportingLedgerListsPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyRLReportingLedgersToAccLedgersMapping(), "RL Reporting Ledgers to Acc Ledgers Mapping Failed");
		masterPage.goToUploadListTBListPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyUploadListDBList(), "Upload List DB List Failed");
		//softAssert.assertTrue(masterPage.verifyUploadListDBRLMapLists(), "Upload List DB RL Map Lists Failed");
		softAssert.assertTrue(masterPage.verifyUploadListFasTBList(), "Upload List Fas TB List Failed");
		softAssert.assertTrue(masterPage.verifyUploadListUnfreezeList(), "Upload List Unfreeze List Failed");
		masterPage.goToUploadsTBPage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyUploadsBudgetData(), "Uploads Budget Data Failed");
		softAssert.assertTrue(masterPage.verifyUploadsDBUpload(), "Uploads DB Upload Failed");
		softAssert.assertTrue(masterPage.verifyUploadsFasTBUpload(), "Uploads Fas TB Upload Failed");
		softAssert.assertTrue(masterPage.verifyUploadsFasTBValidate(), "Uploads Fas TB Validate Failed");
		softAssert.assertTrue(masterPage.verifyUploadsOperatingMetricsData(), "Uploads Operating Metrics Data Failed");
		softAssert.assertTrue(masterPage.verifyUploadsTrialComputation(), "Uploads Trial Computation Failed");
		masterPage.goToUserDetailsUserRolePage();
		TestUtil.WaitTill_PageLoads(10);
		softAssert.assertTrue(masterPage.verifyUserDetailsUser(), "User Details User Faied");
		softAssert.assertTrue(masterPage.verifyUserDetailsUserGroup(), "User Details User Group Failed");
	
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
